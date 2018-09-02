package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An {@link AnalysisRequest} implementation for a single phrase.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhraseAnalysisRequest implements AnalysisRequest {

    private String phrase;
    private String requestId;

    /**
     * Constructor.
     *
     * @param phrase phrase to be analysed
     */
    @JsonCreator
    public PhraseAnalysisRequest(@JsonProperty("phrase") String phrase) {
        this.phrase = phrase;
    }

    /**
     * Constructor.
     *
     * @param phrase phrase to be analysed
     * @param requestId the request id
     */
    public PhraseAnalysisRequest(String phrase, String requestId) {
        this.phrase = phrase;
        this.requestId = requestId;
    }

    /**
     * Retrieve the phrase.
     *
     * @return String the phrase
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * Retrieve the request id.
     *
     * @return String the request id
     */
    public String getRequestId() {
        return requestId;
    }
}
