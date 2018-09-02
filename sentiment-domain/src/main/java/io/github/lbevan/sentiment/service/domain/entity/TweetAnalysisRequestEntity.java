package io.github.lbevan.sentiment.service.domain.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Class that models a Tweet Analysis Request database entity.
 */
@Document(collection = "tweetAnalysisRequest")
public class TweetAnalysisRequestEntity extends BaseAnalysisRequestEntity {

    @Field("tweetId")
    private String tweetId;

    @PersistenceConstructor
    public TweetAnalysisRequestEntity(String requestId, String tweetId) {
        this.requestId = requestId;
        this.tweetId = tweetId;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }
}
