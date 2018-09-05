package io.github.lbevan.sentiment.service.domain.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Model class for a sentence analysis result.
 */
@Document(collection = "sentence")
public class Sentence extends BaseEntity {

    @Field("text")
    private String text;

    @Field("sentiment")
    private String sentiment;

    @Field("sentimentScore")
    private int sentimentScore;

    @Field("sentimentDistribution")
    private double[] sentimentDistribution;

    /**
     * Constructor.
     *
     * @param sentiment
     * @param sentimentScore
     * @param sentimentDistribution
     */
    @PersistenceConstructor
    public Sentence(String text, String sentiment, int sentimentScore, double[] sentimentDistribution) {
        this.text = text;
        this.sentiment = sentiment;
        this.sentimentScore = sentimentScore;
        this.sentimentDistribution = sentimentDistribution;
    }

    /**
     * Retrieve the text.
     *
     * @return String text
     */
    public String getText() {
        return text;
    }

    /**
     * Retrieve the sentiment.
     *
     * @return String sentiment
     */
    public String getSentiment() {
        return sentiment;
    }

    /**
     * Retrieve the sentiment score.
     *
     * @return String sentiment score
     */
    public int getSentimentScore() {
        return sentimentScore;
    }

    /**
     * Retrieve the sentiment distribution (ordered by sentiment type 0 - 4)
     *
     * @return Float[] sentiment distribution
     */
    public double[] getSentimentDistribution() {
        return sentimentDistribution;
    }
}
