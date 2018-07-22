package io.github.lbevan.sentiment.engine;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import io.github.lbevan.sentiment.domain.AnalysisResult;

public class AnalysisEngine {

    private StanfordCoreNLP engine;

    public AnalysisEngine() {
        engine = new StanfordCoreNLP("sentiment.properties");
    }

    public AnalysisResult calculateSentiment(String contents) {
        int mainSentiment = 0;
        String sentimentString = "";
        int longest = 0;
        Annotation annotation = engine.process(contents);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
            sentimentString = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            String partText = sentence.toString();
            if (partText.length() > longest) {
                mainSentiment = sentiment;
                longest = partText.length();
            }

        }

        return new AnalysisResult(sentimentString, mainSentiment);
    }
}
