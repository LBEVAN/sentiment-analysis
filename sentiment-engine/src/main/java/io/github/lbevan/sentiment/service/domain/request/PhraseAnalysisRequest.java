package io.github.lbevan.sentiment.service.domain.request;

/**
 * An {@link AnalysisRequest} implementation for a single phrase.
 */
public class PhraseAnalysisRequest implements AnalysisRequest {

    private String phrase;

    /**
     * Constructor.
     *
     * @param phrase phrase to be analysed
     */
    public PhraseAnalysisRequest(String phrase) {
        this.phrase = phrase;
    }

    /**
     * Retrieve the phrase.
     *
     * @return String the phrase
     */
    public String getPhrase() {
        return phrase;
    }
}
