package io.github.lbevan.sentiment.service.domain.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Class that models a Phrase Analysis Request database entity.
 */
@Document(collection = "phraseAnalysisRequest")
public class PhraseAnalysisRequestEntity extends BaseAnalysisRequestEntity {

    @Field("phrase")
    private String phrase;

    @PersistenceConstructor
    public PhraseAnalysisRequestEntity(String requestId, String phrase) {
        this.requestId = requestId;
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
