package io.github.lbevan.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Arrays;
import java.util.List;

@Configuration
@DependsOn("rabbitMQProperties")
public class RabbitMQConfiguration {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(rabbitMQProperties.getExchange());
    }

    @Bean
    public List<Queue> queues() {
        return Arrays.asList(
                new Queue(rabbitMQProperties.getTextRequestQueue()),
                new Queue(rabbitMQProperties.getTweetRequestQueue()),
                new Queue(rabbitMQProperties.getHashtagRequestQueue())
        );
    }

    @Bean
    List<Binding> bindings() {
        return Arrays.asList(
                new Binding(rabbitMQProperties.getTextRequestQueue(),
                        Binding.DestinationType.QUEUE,
                        rabbitMQProperties.getExchange(),
                        rabbitMQProperties.getTextRequestQueueKey(),
                        null),
                new Binding(rabbitMQProperties.getTweetRequestQueue(),
                        Binding.DestinationType.QUEUE,
                        rabbitMQProperties.getExchange(),
                        rabbitMQProperties.getTweetRequestQueueKey(),
                        null),
                new Binding(rabbitMQProperties.getHashtagRequestQueue(),
                        Binding.DestinationType.QUEUE,
                        rabbitMQProperties.getExchange(),
                        rabbitMQProperties.getHashtagRequestQueueKey(),
                        null)
        );
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(rabbitMQProperties.getHost(),
                rabbitMQProperties.getPort());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

//    @Bean
//    public RetryOperationsInterceptor interceptor() {
//        return RetryInterceptorBuilder.stateless()
//                .maxAttempts(5)
//                .
//                .recoverer(new RejectAndDontRequeueRecoverer()
//
//
//        new RepublishMessageRecoverer(rabbitTemplate(connectionFactory()),
//                        "bar",
//                        "baz"))
//                .build();
//    }

}
