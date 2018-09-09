package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.exception.AnalysisRequestException;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;

import java.util.LinkedList;

/**
 * A {@link PipelineAdapter} implementation for a single tweet request.
 */
public class TweetPipelineAdapter implements PipelineAdapter {

    private TweetAnalysisRequestDto request;
    private TwitterService twitterService;

    /**
     * Constructor.
     *
     * @param request the tweet request
     */
    public TweetPipelineAdapter(TweetAnalysisRequestDto request) {
        this.request = request;
        this.twitterService = SpringBeanUtil.getBean(TwitterService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Payload adapt() throws AnalysisRequestException {
        try {
            final String tweetId = getTweetIdFromLink(request.getTweetLink());

            Tweet tweet = twitterService.getTweetById(tweetId);

            LinkedList<String> payloadData = new LinkedList<>();
            payloadData.add(tweet.getText());

            return new Payload(request.getRequestId(), payloadData);
        } catch (TwitterServiceException e) {
            throw new AnalysisRequestException("Exception caught adapting tweet request to payload.", e);
        }
    }

    /**
     * Get the tweet id from the specified link.
     *
     * @param tweetLink link to search
     */
    private String getTweetIdFromLink(final String tweetLink) {
        // get the tweet id which is the data after last slash
        return tweetLink.substring(tweetLink.lastIndexOf("/") + 1).trim();
    }
}
