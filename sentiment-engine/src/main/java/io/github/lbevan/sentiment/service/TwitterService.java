package io.github.lbevan.sentiment.service;

import io.github.lbevan.sentiment.domain.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for interacting with the Twitter REST API.
 */
@Service
public class TwitterService {

    private static final String BASE_API = "https://api.twitter.com/1.1/";

    @Autowired
    @Qualifier("twitterRestTemplate")
    private RestTemplate twitterRestTemplate;

    /**
     * Retrieve a Tweet by it's Id.
     *
     * @param id
     * @return Tweet
     */
    public Tweet getTweetById(final String id) {
        Tweet tweet = twitterRestTemplate.getForObject(BASE_API + "statuses/show.json?id=" + id, Tweet.class);
        return tweet;
    }
}
