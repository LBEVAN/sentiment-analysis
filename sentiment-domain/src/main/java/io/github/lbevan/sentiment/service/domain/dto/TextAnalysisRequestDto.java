package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * An {@link AnalysisRequest} implementation for text.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextAnalysisRequestDto implements AnalysisRequest {

    @NotNull
    @Size(min = 1)
    private String text;

    private String requestId;

    /**
     * Constructor.
     *
     * @param text the text to be analysed
     */
    @JsonCreator
    public TextAnalysisRequestDto(@JsonProperty("text") String text) {
        this.text = text;
    }

    /**
     * Constructor.
     *
     * @param text the text to be analysed
     * @param requestId the request id
     */
    public TextAnalysisRequestDto(String text, String requestId) {
        this.text = text;
        this.requestId = requestId;
    }

    /**
     * Retrieve the text.
     *
     * @return String the text
     */
    public String getText() {
        return text;
    }

    /**
     * Retrieve the request id.
     *
     * @return String the request id
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
