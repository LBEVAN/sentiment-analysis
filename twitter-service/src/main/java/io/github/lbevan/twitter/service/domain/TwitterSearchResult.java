package io.github.lbevan.twitter.service.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Model class for the results that return from the twitter search api.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterSearchResult {

    private List<Tweet> tweets;

    /**
     * Constructor.
     *
     * @param tweets
     */
    @JsonCreator
    public TwitterSearchResult(@JsonProperty("statuses") List<Tweet> tweets) {
        this.tweets = tweets;
    }

    /**
     * Retrieve the list of tweets.
     *
     * @return List<Tweet>
     */
    public List<Tweet> getTweets() {
        return tweets;
    }
}
