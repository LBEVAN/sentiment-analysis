package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An {@link AnalysisRequest} implementation for a single tweet.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetAnalysisRequestDto implements AnalysisRequest {

    private String tweetId;
    private String requestId;

    /**
     * Constructor.
     *
     * @param tweetId id of the tweet to process
     */
    @JsonCreator
    public TweetAnalysisRequestDto(@JsonProperty("tweetId") String tweetId) {
        this.tweetId = tweetId;
    }

    /**
     * Constructor.
     *
     * @param tweetId id of the tweet to process
     */
    public TweetAnalysisRequestDto(String tweetId, String requestId) {
        this.tweetId = tweetId;
        this.requestId = requestId;
    }

    /**
     * Retrieve the tweet id.
     *
     * @return String tweetId
     */
    public String getTweetId() {
        return tweetId;
    }

    /**
     * Retrieve the request id.
     *
     * @return String request id
     */
    public String getRequestId() {
        return requestId;
    }
}
