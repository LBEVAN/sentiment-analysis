package io.github.lbevan.sentiment.service.domain.entity;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Model class for a sentence analysis result.
 */
@JsonDeserialize(using = SentenceDeserializer.class)
@Document(collection = "sentence")
public class Sentence extends BaseEntity {

    @Field("sentimentValue")
    private String sentimentValue;

    @Field("sentiment")
    private String sentiment;

    @Field("sentimentTree")
    private String sentimentTree;

    @Field("sentimentDistribution")
    private Float[] sentimentDistribution;

    /**
     * Constructor.
     *
     * @param sentimentValue
     * @param sentiment
     * @param sentimentTree
     * @param sentimentDistribution
     */
    @PersistenceConstructor
    public Sentence(String sentimentValue, String sentiment, String sentimentTree, Float[] sentimentDistribution) {
        this.sentimentValue = sentimentValue;
        this.sentiment = sentiment;
        this.sentimentTree = sentimentTree;
        this.sentimentDistribution = sentimentDistribution;
    }

    /**
     * Retrieve the sentiment value.
     *
     * @return String sentiment value
     */
    public String getSentimentValue() {
        return sentimentValue;
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
     * Retrieve the sentiment tree.
     *
     * @return String sentiment tree
     */
    public String getSentimentTree() {
        return sentimentTree;
    }

    /**
     * Retrieve the sentiment distribution (ordered by sentiment type 0 - 4)
     *
     * @return Float[] sentiment distribution
     */
    public Float[] getSentimentDistribution() {
        return sentimentDistribution;
    }
}
