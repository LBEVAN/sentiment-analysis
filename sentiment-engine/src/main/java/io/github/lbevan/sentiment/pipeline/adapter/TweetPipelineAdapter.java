package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.twitter.service.domain.Tweet;
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
    public Payload adapt() {
        // todo: pre-validation? is it still available? throw exception if not?

        Tweet tweet = twitterService.getTweetById(request.getTweetId());

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(tweet.getText());

        return new Payload(request.getRequestId(), payloadData);
    }
}
