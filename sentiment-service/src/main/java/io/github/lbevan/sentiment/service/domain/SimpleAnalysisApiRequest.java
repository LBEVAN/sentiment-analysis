package io.github.lbevan.sentiment.service.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Domain model for a simple API request that requires only a string of data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleAnalysisApiRequest {

    private String data;

    /**
     * Constructor.
     *
     * @param data
     */
    @JsonCreator
    public SimpleAnalysisApiRequest(@JsonProperty("data") String data) {
        this.data = data;
    }

    /**
     * Retrieve the data.
     *
     * @return String data
     */
    public String getData() {
        return data;
    }
}
