package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.domain.dto.TextAnalysisRequestDto;

import java.util.LinkedList;

/**
 * A {@link PipelineAdapter} implementation for a single Text analysis request.
 */
public class TextPipelineAdapter implements PipelineAdapter {

    private TextAnalysisRequestDto request;

    /**
     * Constructor.
     *
     * @param request the text request
     */
    public TextPipelineAdapter(TextAnalysisRequestDto request) {
        this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Payload adapt() {
        String text = request.getText();

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(text);

        return new Payload(request.getRequestId(), payloadData);
    }
}
