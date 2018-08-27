package io.github.lbevan.sentiment.engine;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.JSONOutputter;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import io.github.lbevan.sentiment.domain.result.Sentence;
import io.github.lbevan.sentiment.domain.result.AnalysisResult;
import io.github.lbevan.sentiment.domain.result.Sentiment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.util.Precision;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
        // perform analysis
        List<Integer> sentimentScores = new ArrayList<>();
        Annotation annotation = engine.process(input);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentimentScore = RNNCoreAnnotations.getPredictedClass(tree);
            sentimentScores.add(sentimentScore);
        }

        // get the results
        List<Sentence> sentences = getAnalysedSentences(annotation);
        float averageSentimentScore = calculateAverageSentimentScore(sentimentScores);
        String averageSentiment = Sentiment.getDescriptionByScore(averageSentimentScore);

        return new AnalysisResult(sentences, averageSentiment, averageSentimentScore);
    }

    /**
     * Retrieve the analysed sentences from the annotation.
     *
     * @param annotation the analysis
     * @return List the list of sentences
     */
    private List<Sentence> getAnalysedSentences(Annotation annotation) {
        List<Sentence> sentences = new ArrayList<>();
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            // get the analysis json
            String json = JSONOutputter.jsonPrint(annotation);

            // convert the analysis to our domain so we have only the attributes we want
            JsonNode sentencesNode = objectMapper.readTree(json).get("sentences");
            sentences = objectMapper.readValue(sentencesNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Sentence.class));
        } catch (IOException e) {
            LOGGER.error("An error occurred analysing the data provided!");
        }
        return sentences;
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
