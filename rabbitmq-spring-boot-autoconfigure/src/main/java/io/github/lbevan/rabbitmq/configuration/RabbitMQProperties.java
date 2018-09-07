package io.github.lbevan.rabbitmq.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitMQProperties {

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port}")
    private int port;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue.request.text}")
    private String textRequestQueue;

    @Value("${rabbitmq.queue.request.text.key}")
    private String textRequestQueueKey;

    @Value("${rabbitmq.queue.request.tweet}")
    private String tweetRequestQueue;

    @Value("${rabbitmq.queue.request.tweet.key}")
    private String tweetRequestQueueKey;

    @Value("${rabbitmq.queue.request.hashtag}")
    private String hashtagRequestQueue;

    @Value("${rabbitmq.queue.request.hashtag.key}")
    private String hashtagRequestQueueKey;

    @Value("${rabbitmq.queue.request.document}")
    private String documentRequestQueue;

    @Value("${rabbitmq.queue.request.document.key}")
    private String documentRequestQueueKey;


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getExchange() {
        return exchange;
    }

    public String getTextRequestQueue() {
        return textRequestQueue;
    }

    public String getTextRequestQueueKey() {
        return textRequestQueueKey;
    }

    public String getTweetRequestQueue() {
        return tweetRequestQueue;
    }

    public String getTweetRequestQueueKey() {
        return tweetRequestQueueKey;
    }

    public String getHashtagRequestQueue() {
        return hashtagRequestQueue;
    }

    public String getHashtagRequestQueueKey() {
        return hashtagRequestQueueKey;
    }

    public String getDocumentRequestQueue() {
        return documentRequestQueue;
    }

    public String getDocumentRequestQueueKey() {
        return documentRequestQueueKey;
    }
}
