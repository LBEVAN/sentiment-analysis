package io.github.lbevan.sentiment.engine;

import io.github.lbevan.sentiment.domain.AnalysisResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAnalysisEngine {

    @Test
    public void testPositiveSentimentScenario() {
        AnalysisEngine analysisEngine = new AnalysisEngine();

        AnalysisResult analysisResult = analysisEngine.calculateSentiment("I love Monday mornings!");

        assertTrue(analysisResult.getSentiment().equals("Positive"));

        System.out.println(analysisResult.getSentiment() + " : " + analysisResult.getSentimentScore());
    }

    @Test
    public void testNegativeSentimentScenario() {
        AnalysisEngine analysisEngine = new AnalysisEngine();

        AnalysisResult analysisResult = analysisEngine.calculateSentiment("I hate Monday mornings!");

        assertTrue(analysisResult.getSentiment().equals("Negative"));

        System.out.println(analysisResult.getSentiment() + " : " + analysisResult.getSentimentScore());
    }
}
