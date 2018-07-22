package io.github.lbevan.sentiment.domain;

public class PhraseAnalysisRequest {

    private String phrase;

    public PhraseAnalysisRequest(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
