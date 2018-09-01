package io.github.lbevan.sentiment.service.domain.result;


import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates a singular analysis result (with potentially many sentences).
 */
public class AnalysisResult implements Serializable {

    private List<Sentence> sentences;
    private String averageSentiment;
    private float averageSentimentScore;

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
