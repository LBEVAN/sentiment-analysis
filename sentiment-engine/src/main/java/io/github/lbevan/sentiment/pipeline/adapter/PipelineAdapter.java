package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;

/**
 * Marker interface for pipeline adapters.
 */
public interface PipelineAdapter {

    /**
     * Adapt the input into the payload, ready for pipeline processing.
     *
     * @return Payload
     */
    Payload adapt();
}
