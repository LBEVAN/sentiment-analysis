package io.github.lbevan.sentiment.domain;

public class AnalysisResult {

    private String sentiment;
    private int sentimentScore;

    public AnalysisResult(String sentiment, int sentimentScore) {
        this.sentiment = sentiment;
        this.sentimentScore = sentimentScore;
    }

    public String getSentiment() {
        return sentiment;
    }

    public int getSentimentScore() {
        return sentimentScore;
    }
}
