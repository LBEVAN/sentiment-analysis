package io.github.lbevan.sentiment.engine;

import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link AnalysisEngine}.
 */
public class TestAnalysisEngine {

    @Test
    public void whenPositiveStringIsInput_thenPositiveSentimentIsCalculated() {
        AnalysisEngine analysisEngine = new AnalysisEngine();

        AnalysisResult analysisResult = analysisEngine.calculateSentiment("I love Monday mornings!");

        assertTrue(analysisResult.getSentiment().equals("Positive"));

        System.out.println(analysisResult.getSentiment() + " : " + analysisResult.getSentimentScore());
    }

    @Test
    public void whenNegativeStringIsInput_thenNegativeSentimentIsCalculated() {
        AnalysisEngine analysisEngine = new AnalysisEngine();

        AnalysisResult analysisResult = analysisEngine.calculateSentiment("I hate Monday mornings!");

        assertTrue(analysisResult.getSentiment().equals("Negative"));

        System.out.println(analysisResult.getSentiment() + " : " + analysisResult.getSentimentScore());
    }

    @Test
    public void whenMultipleSentencesAreInput_ThenAllSentencesAreProcessed() {
        AnalysisEngine analysisEngine = new AnalysisEngine();

        AnalysisResult analysisResult = analysisEngine.calculateSentiment("I love Monday mornings! I hate Monday mornings!");

        assertEquals(2, analysisResult.getSentences().size());
        assertEquals(Sentiment.POSITIVE.getDescription(), analysisResult.getSentences().get(0).getSentiment());
        assertEquals(Sentiment.NEGATIVE.getDescription(), analysisResult.getSentences().get(1).getSentiment());
    }
}
