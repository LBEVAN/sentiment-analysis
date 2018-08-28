package io.github.lbevan.twitter.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class for a Tweet.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

    private Long id;
    private String text;

    /**
     * Constructor.
     *
     * @param id
     * @param text
     */
    @JsonCreator
    public Tweet(@JsonProperty("id") Long id,
                 @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * Retrieve the id of the tweet.
     *
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    /**
     * Retrieve the text of the tweet.
     *
     * @return String text
     */
    public String getText() {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
