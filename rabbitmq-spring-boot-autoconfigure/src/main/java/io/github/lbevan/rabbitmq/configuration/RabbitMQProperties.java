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

    @Value("${rabbitmq.queue.request.phrase}")
    private String phraseRequestQueue;

    @Value("${rabbitmq.queue.request.phrase.key}")
    private String phraseRequestQueueKey;

    @Value("${rabbitmq.queue.request.tweet}")
    private String tweetRequestQueue;

    @Value("${rabbitmq.queue.request.tweet.key}")
    private String tweetRequestQueueKey;


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getExchange() {
        return exchange;
    }

    public String getPhraseRequestQueue() {
        return phraseRequestQueue;
    }

    public String getPhraseRequestQueueKey() {
        return phraseRequestQueueKey;
    }

    public String getTweetRequestQueue() {
        return tweetRequestQueue;
    }

    public String getTweetRequestQueueKey() {
        return tweetRequestQueueKey;
    }
}
