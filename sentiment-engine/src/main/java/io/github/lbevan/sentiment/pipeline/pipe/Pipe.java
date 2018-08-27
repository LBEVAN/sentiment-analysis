package io.github.lbevan.sentiment.pipeline.pipe;

import io.github.lbevan.sentiment.pipeline.Payload;

/**
 * A pipe interface for use with {@link io.github.lbevan.sentiment.pipeline.Pipeline}.
 */
public interface Pipe {

    /**
     * Process the payload.
     *
     * @param payload
     */
    void process(Payload payload);
}
