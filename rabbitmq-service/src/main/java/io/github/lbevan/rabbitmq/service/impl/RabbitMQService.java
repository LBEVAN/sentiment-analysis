package io.github.lbevan.rabbitmq.service.impl;

import io.github.lbevan.rabbitmq.configuration.RabbitMQProperties;
import io.github.lbevan.sentiment.service.domain.dto.DocumentAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.dto.HashtagAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.dto.TextAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    @Autowired
    public RabbitMQService(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    public void sendTextAnalysisRequest(final TextAnalysisRequestDto request) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getTextRequestQueueKey(),
                request);
    }

    public void sendTweetAnalysisRequest(final TweetAnalysisRequestDto request) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getTweetRequestQueueKey(),
                request);
    }

    public void sendHashtagAnalysisRequest(final HashtagAnalysisRequestDto request) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getHashtagRequestQueueKey(),
                request);
    }

    public void sendDocumentAnalysisRequest(final DocumentAnalysisRequestDto request) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getDocumentRequestQueueKey(),
                request);
    }
}
