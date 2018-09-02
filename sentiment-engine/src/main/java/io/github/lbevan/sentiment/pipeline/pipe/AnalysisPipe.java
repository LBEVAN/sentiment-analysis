package io.github.lbevan.sentiment.pipeline.pipe;

import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.engine.AnalysisEngine;
import io.github.lbevan.sentiment.pipeline.Payload;

/**
 * A {@link Pipe} implementation.
 * Executes the sentiment analysis on the given input items.
 */
public class AnalysisPipe implements Pipe {

    private AnalysisEngine analysisEngine;

    /**
     * Constructor.
     */
    public AnalysisPipe() {
        this.analysisEngine = new AnalysisEngine();
    }

    /**
     * Iterate over the payload input, calculating the sentiment for each item.
     */
    @Override
    public void process(Payload payload) {
        for(String input : payload.getInput()) {
            // calculate the sentiment
            AnalysisResult analysisResult = analysisEngine.calculateSentiment(input);

            // add the requestId to the result and add to list of results
            payload.addResult(new AnalysisResult(analysisResult.getSentences(),
                    analysisResult.getSentiment(),
                    analysisResult.getSentimentScore(),
                    payload.getRequestId()));
        }
    }
}
