package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lbevan.sentiment.service.domain.validation.BasicValidation;
import io.github.lbevan.sentiment.service.domain.validation.ExtensiveValidation;
import io.github.lbevan.sentiment.service.domain.validation.tweet.TweetLink;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * An {@link AnalysisRequest} implementation for a single tweet.
 */
@GroupSequence({ BasicValidation.class, ExtensiveValidation.class, TweetAnalysisRequestDto.class })
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetAnalysisRequestDto implements AnalysisRequest {

    @NotNull(groups = BasicValidation.class)
    @Size(min = 1, groups = BasicValidation.class)
    @TweetLink(groups = ExtensiveValidation.class)
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
