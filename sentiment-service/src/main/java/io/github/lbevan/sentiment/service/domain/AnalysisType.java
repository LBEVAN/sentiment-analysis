package io.github.lbevan.sentiment.service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The types of Analysis supported.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AnalysisType {

    TEXT("Text", "TEXT", "Analyse a piece of text"),
    TWITTER_TWEET("Twitter - Tweet", "TWITTER_TWEET", "Analyse a single Tweet");

    private String name;
    private String code;
    private String description;

    /**
     * Constructor.
     *
     * @param name
     * @param code
     * @param description
     */
    AnalysisType(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    /**
     * Retrieve the name.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the code.
     *
     * @return String code
     */
    public String getCode() {
        return code;
    }

    /**
     * Retrieve the description.
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }
}
