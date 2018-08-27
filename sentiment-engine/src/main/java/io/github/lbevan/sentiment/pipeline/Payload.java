package io.github.lbevan.sentiment.pipeline;

import io.github.lbevan.sentiment.domain.result.AnalysisResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Encapsulates the data that travels through the pipeline.
 */
public final class Payload {

    private LinkedList<String> input;
    private List<AnalysisResult> results;

    /**
     * Constructor.
     *
     * @param input
     */
    public Payload(LinkedList<String> input) {
        this.input = input;
        results = new ArrayList<>(input.size());
    }

    /**
     * Retrieve the input.
     *
     * @return LinkedList input
     */
    public LinkedList<String> getInput() {
        return input;
    }

    /**
     * Retrieve the results.
     *
     * @return List results
     */
    public List<AnalysisResult> getResults() {
        return results;
    }

    /**
     * Add an analysis result.
     *
     * @param result
     */
    public void addResult(AnalysisResult result) {
        results.add(result);
    }
}
