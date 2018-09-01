package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.service.domain.result.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.request.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link PhraseAnalysis}.
 */
public class TestPhraseAnalysis {

    @Test
    public void whenRequestReceived_thenRequestIsProcessedAndResultIsReturned() {
        PhraseAnalysisRequest request = new PhraseAnalysisRequest("This analysis engine is the best!");

        List<AnalysisResult> analysisResult = new PhraseAnalysis().receiveRequest(request);

        assertEquals(1, analysisResult.size());
        assertTrue(analysisResult.get(0).getSentiment().equals(Sentiment.VERY_POSITIVE.getDescription()));
    }
}
