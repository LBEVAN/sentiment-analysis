package io.github.lbevan.sentiment.service.domain.result;

/**
 * Enum for different sentiment types.
 */
public enum Sentiment {

    VERY_NEGATIVE(0, "Very Negative"),
    NEGATIVE(1, "Negative"),
    NEUTRAL(2, "Neutral"),
    POSITIVE(3, "Positive"),
    VERY_POSITIVE(4, "Very Positive");

    private int score;
    private String description;

    Sentiment(int score, String description) {
        this.score = score;
        this.description = description;
    }

    /**
     * Retrieve the description of the sentiment.
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieve the description of a sentiment based on the given score.
     *
     * @param score
     * @return String description
     */
    public static String getDescriptionByScore(float score) {
        return Sentiment.values()[Math.round(score)].description;
    }
}
