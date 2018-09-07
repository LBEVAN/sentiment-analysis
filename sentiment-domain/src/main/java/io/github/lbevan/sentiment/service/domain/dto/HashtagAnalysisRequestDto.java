package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An {@link AnalysisRequest} implementation for hashtag search.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HashtagAnalysisRequestDto implements AnalysisRequest {

    private String hashtag;
    private String requestId;

    /**
     * Constructor.
     *
     * @param hashtag hashtag to search
     */
    @JsonCreator
    public HashtagAnalysisRequestDto(@JsonProperty("hashtag") String hashtag) {
        this.hashtag = hashtag;
    }

    /**
     * Constructor.
     *
     * @param hashtag hashtag to search
     */
    public HashtagAnalysisRequestDto(String hashtag, String requestId) {
        this.hashtag = hashtag;
        this.requestId = requestId;
    }

    /**
     * Retrieve the hashtag.
     *
     * @return String hashtag
     */
    public String getHashtag() {
        return hashtag;
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
