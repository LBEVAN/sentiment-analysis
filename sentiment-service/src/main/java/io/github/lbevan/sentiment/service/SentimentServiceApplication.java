package io.github.lbevan.sentiment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMongoRepositories("io.github.lbevan.sentiment.repository.impl")
@ComponentScan(basePackages = { "io.github.lbevan" })
public class SentimentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentimentServiceApplication.class, args);
    }
}
