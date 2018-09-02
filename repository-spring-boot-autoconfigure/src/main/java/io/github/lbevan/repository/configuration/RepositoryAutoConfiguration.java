package io.github.lbevan.repository.configuration;

import com.mongodb.MongoClient;
import io.github.lbevan.sentiment.repository.event.CascadeSaveEventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@PropertySource("classpath:repository.properties")
public class RepositoryAutoConfiguration {

    @Value("${repository.host}")
    private String host;

    @Value("${repository.port}")
    private int port;

    @Value("${repository.database}")
    private String database;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(host, port), database);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Bean
    public CascadeSaveEventListener cascadeSaveEventListener() {
        return new CascadeSaveEventListener();
    }
}
