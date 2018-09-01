package io.github.lbevan.sentiment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "io.github.lbevan" })
public class SentimentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentimentServiceApplication.class, args);
    }
}
