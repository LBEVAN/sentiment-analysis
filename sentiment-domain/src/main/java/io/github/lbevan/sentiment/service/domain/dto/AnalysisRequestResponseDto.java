package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Analysis request response data transfer object.
 */
public class AnalysisRequestResponseDto {

    private String requestId;

    /**
     * Constructor.
     *
     * @param requestId
     */
    @JsonCreator
    public AnalysisRequestResponseDto(@JsonProperty("requestId") String requestId) {
        this.requestId = requestId;
    }

    /**
     * Retrieve the request Id.
     *
     * @return String requestId
     */
    public String getRequestId() {
        return requestId;
    }
}
