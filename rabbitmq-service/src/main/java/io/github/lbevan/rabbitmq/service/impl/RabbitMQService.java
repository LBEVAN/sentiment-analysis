package io.github.lbevan.rabbitmq.service.impl;

import io.github.lbevan.rabbitmq.configuration.RabbitMQProperties;
import io.github.lbevan.sentiment.service.domain.dto.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequest;
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

    public void sendPhraseAnalysisRequest(final PhraseAnalysisRequest request) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getPhraseRequestQueueKey(),
                request);
    }

    public void sendTweetAnalysisRequest(final TweetAnalysisRequest request) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getTweetRequestQueueKey(),
                request);
    }
}
