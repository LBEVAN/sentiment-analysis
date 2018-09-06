package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An {@link AnalysisRequest} implementation for a single tweet.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetAnalysisRequestDto implements AnalysisRequest {

    private String tweetLink;
    private String requestId;

    /**
     * Constructor.
     *
     * @param tweetLink id of the tweet to process
     */
    @JsonCreator
    public TweetAnalysisRequestDto(@JsonProperty("tweetLink") String tweetLink) {
        this.tweetLink = tweetLink;
    }

    /**
     * Constructor.
     *
     * @param tweetLink link of the tweet to process
     */
    public TweetAnalysisRequestDto(String tweetLink, String requestId) {
        this.tweetLink = tweetLink;
        this.requestId = requestId;
    }

    /**
     * Retrieve the tweet id.
     *
     * @return String tweetLink
     */
    public String getTweetLink() {
        return tweetLink;
    }

    /**
     * Retrieve the request id.
     *
     * @return String request id
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Set the request id.
     *
     * @param requestId the request id to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
