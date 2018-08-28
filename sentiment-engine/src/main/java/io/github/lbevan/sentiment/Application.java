package io.github.lbevan.sentiment;

import io.github.lbevan.sentiment.engine.AnalysisEngine;
import io.github.lbevan.twitter.domain.Tweet;
import io.github.lbevan.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "io.github.lbevan" })
public class Application implements CommandLineRunner {

    @Autowired
    private TwitterService twitterService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        Tweet tweet = twitterService.getTweetById("1017825387785719808");
        System.out.println(new AnalysisEngine().calculateSentiment(tweet.getText()).getSentiment());
    }
}
