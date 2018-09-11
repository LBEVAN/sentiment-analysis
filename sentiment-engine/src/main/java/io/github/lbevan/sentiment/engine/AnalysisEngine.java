package io.github.lbevan.sentiment.engine;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.entity.Sentence;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the CoreNLP functions and the workings
 * of retrieving the sentiment from a given input.
 */
public class AnalysisEngine {

    private static final Log LOGGER = LogFactory.getLog(AnalysisEngine.class);

    private StanfordCoreNLP engine;

    /**
     * Constructor.
     */
    public AnalysisEngine() {
        engine = new StanfordCoreNLP("sentiment.properties");
    }

    /**
     * Calculate the sentiment of the given string.
     *
     * @param input string containing one or multiple sentences
     * @return AnalysisResult the analysis
     */
    public AnalysisResult calculateSentiment(String input) {
        List<Integer> sentimentScores = new ArrayList<>();
        List<Sentence> sentences = new ArrayList<>();

        // perform analysis
        Annotation annotation = engine.process(input);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

            // get the sentence information
            String text = sentence.get(CoreAnnotations.TextAnnotation.class);
            int sentimentScore = RNNCoreAnnotations.getPredictedClass(tree);
            String sentiment = Sentiment.getDescriptionByScore(sentimentScore);
            double[] sentimentDistribution = RNNCoreAnnotations.getPredictions(tree).getMatrix().getData();

            sentences.add(new Sentence(text, sentiment, sentimentScore, sentimentDistribution));
            sentimentScores.add(sentimentScore);
        }

        // get the results
        float averageSentimentScore = calculateAverageSentimentScore(sentimentScores);
        String averageSentiment = Sentiment.getDescriptionByScore(averageSentimentScore);

        return new AnalysisResult(input, sentences, averageSentiment, averageSentimentScore);
    }

    /**
     * Calculate the average sentiment score given a list of scores.
     *
     * @param scores list of scores
     * @return float to 2dp
     */
    private float calculateAverageSentimentScore(List<Integer> scores) {
        // todo: look into a better weighted average?
        float totalScore = 0 ;

        for(Integer score : scores) {
            totalScore += score;
        }

        return Precision.round(totalScore / scores.size(), 2);
    }
}
