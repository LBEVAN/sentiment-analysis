package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.domain.dto.PhraseAnalysisRequest;

import java.util.LinkedList;

/**
 * A {@link PipelineAdapter} implementation for a single Phrase request.
 */
public class PhrasePipelineAdapter implements PipelineAdapter {

    private PhraseAnalysisRequest request;

    /**
     * Constructor.
     *
     * @param request the phrase request
     */
    public PhrasePipelineAdapter(PhraseAnalysisRequest request) {
        this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Payload adapt() {
        String phrase = request.getPhrase();

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(phrase);

        return new Payload(request.getRequestId(), payloadData);
    }
}
