package io.github.lbevan.sentiment.domain.request;

/**
 * An {@link AnalysisRequest} implementation for a single tweet.
 */
public class TweetAnalysisRequest implements AnalysisRequest {

    private String tweetId;

    /**
     * Constructor.
     *
     * @param tweetId id of the tweet to process
     */
    public TweetAnalysisRequest(String tweetId) {
        this.tweetId = tweetId;
    }

    /**
     * Retrieve the tweet id.
     *
     * @return String tweetId
     */
    public String getTweetId() {
        return tweetId;
    }
}
