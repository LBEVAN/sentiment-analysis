package io.github.lbevan.sentiment.util;

import org.springframework.web.client.RestTemplate;

public class TwitterClient {

    private static TwitterClient SINGLETON;
    private static final String BASE_API = "https://api.twitter.com/1.1/";

    private RestTemplate restTemplate;


    protected TwitterClient() {
        restTemplate = new RestTemplate();
    }

    public static TwitterClient getInstance() {
        if(SINGLETON == null) {
            synchronized (TwitterClient.class) {
                if(SINGLETON == null) {
                    SINGLETON = new TwitterClient();
                }
            }
        }
        return SINGLETON;
    }

    public void getTweetById(final String id) {
        final String API = BASE_API + "statuses/show.json?id=";
    }
}
