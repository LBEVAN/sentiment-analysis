package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.domain.AnalysisResult;
import io.github.lbevan.sentiment.domain.PhraseAnalysisRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPhraseAnalysis {

    @Test
    public void testVeryPositivePhraseScenario() {
        PhraseAnalysisRequest request = new PhraseAnalysisRequest("This analysis engine is the best!");

        PhraseAnalysis phraseAnalysis = new PhraseAnalysis();

        AnalysisResult analysisResult = phraseAnalysis.analyse(request);

        assertTrue(analysisResult.getSentiment().equals("Very positive"));
    }
}
