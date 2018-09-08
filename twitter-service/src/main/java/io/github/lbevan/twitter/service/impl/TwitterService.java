package io.github.lbevan.twitter.service.impl;

import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.domain.TwitterSearchResult;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
    public Tweet getTweetById(final String id) throws TwitterServiceException {
        try {
            return twitterRestTemplate.getForObject(BASE_API + "statuses/show.json?id=" + id + "&tweet_mode=extended", Tweet.class);
        } catch(RestClientException e) {
            throw new TwitterServiceException("Exception retrieving Tweet with id of {" + id + " }", e);
        }
    }

    /**
     * Retrieve a list of tweets that include the specified hashtag.
     *
     * @param hashtag hashtag to search for
     * @return List<Tweet>
     */
    public List<Tweet> getTweetsByHashtag(final String hashtag) {
        // encode the parameters
        String queryParams = null;
        try {
            queryParams = URLEncoder.encode(hashtag, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // do the search - attempts to filter out retweets and replies to get to the real tweets!
        TwitterSearchResult searchResult = twitterRestTemplate.getForObject(
                BASE_API + "search/tweets.json?q=" + queryParams + " -filter:retweets -filter:nativeretweets -filter:replies&lang=en&result_type=recent&tweet_mode=extended", TwitterSearchResult.class);
        List<Tweet> foundTweets = searchResult.getTweets();
        return foundTweets;
    }
}
