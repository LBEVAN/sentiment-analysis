package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.service.domain.dto.AnalysisRequest;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;

import java.util.List;

/**
 * Marker interface for RabbitMQ request listeners.
 *
 * @param <T> The type of request to receive.
 */
public interface AnalysisRequestListener<T extends AnalysisRequest> {

    /**
     * Consume a request from a RabbitMQ service.
     * Implementing classes should annotate with @RabbitListener
     * and point to the relevant queue.
     *
     * @param request the request to process.
     */
    void receiveRequest(T request);
}
