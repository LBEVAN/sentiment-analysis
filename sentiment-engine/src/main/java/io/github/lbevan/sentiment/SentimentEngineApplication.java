package io.github.lbevan.sentiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("io.github.lbevan.sentiment.repository.impl")
@ComponentScan(basePackages = { "io.github.lbevan" })
public class SentimentEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentimentEngineApplication.class, args);
    }

//    @Autowired
//    private TwitterService twitterService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(SentimentEngineApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) {
//        Tweet tweet = twitterService.getTweetById("1017825387785719808");
//        System.out.println(new AnalysisEngine().calculateSentiment(tweet.getText()).getSentiment());
//    }
}
