package io.github.lbevan.sentiment.service.domain.entity;


import io.github.lbevan.sentiment.service.domain.annotation.CascadeSave;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates a singular analysis result (with potentially many sentences).
 */
@Document(collection = "analysisResult")
public class AnalysisResult extends BaseEntity {

    @DBRef
    @CascadeSave
    private List<Sentence> sentences;

    @Field("averageSentiment")
    private String averageSentiment;

    @Field("averageSentimentScore")
    private float averageSentimentScore;

    @Field("requestId")
    private String requestId;

    /**
     * Constructor.
     *
     * @param sentences
     * @param averageSentiment
     * @param averageSentimentScore
     */
    @PersistenceConstructor
    public AnalysisResult(List<Sentence> sentences, String averageSentiment, float averageSentimentScore, String requestId) {
        this.sentences = sentences;
        this.averageSentiment = averageSentiment;
        this.averageSentimentScore = averageSentimentScore;
        this.requestId = requestId;
    }

    /**
     * Constructor.
     *
     * @param sentences
     * @param averageSentiment
     * @param averageSentimentScore
     */
    public AnalysisResult(List<Sentence> sentences, String averageSentiment, float averageSentimentScore) {
        this.sentences = sentences;
        this.averageSentiment = averageSentiment;
        this.averageSentimentScore = averageSentimentScore;
    }

    /**
     * Retrieve the list of sentences.
     *
     * @return List of sentences
     */
    public List<Sentence> getSentences() {
        return sentences;
    }

    /**
     * Retrieve the average sentiment.
     *
     * @return String sentiment
     */
    public String getSentiment() {
        return averageSentiment;
    }

    /**
     * Retrieve the average sentiment score.
     *
     * @return Float average sentiment score
     */
    public float getSentimentScore() {
        return averageSentimentScore;
    }
}
