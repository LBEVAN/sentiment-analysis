package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.domain.AnalysisResult;
import io.github.lbevan.sentiment.domain.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.engine.AnalysisEngine;

public class PhraseAnalysis {

    private final AnalysisEngine analysisEngine;

    public PhraseAnalysis() {
        analysisEngine = new AnalysisEngine();
    }

    public AnalysisResult analyse(PhraseAnalysisRequest request) {
        AnalysisResult analysisResult = analysisEngine.calculateSentiment(request.getPhrase());
        return analysisResult;
    }
}
