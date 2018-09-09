package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.HashtagAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.exception.AnalysisRequestException;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;

import java.util.LinkedList;
import java.util.List;

/**
 * A {@link PipelineAdapter} implementation for a Hashtag analysis request.
 */
public class HashtagPipelineAdapter implements PipelineAdapter {

    private HashtagAnalysisRequestDto request;
    private TwitterService twitterService;

    /**
     * Constructor.
     *
     * @param request the hashtag request
     */
    public HashtagPipelineAdapter(HashtagAnalysisRequestDto request) {
        this.request = request;
        this.twitterService = SpringBeanUtil.getBean(TwitterService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Payload adapt() throws AnalysisRequestException {
        try {
            List<Tweet> tweetsByHashtag = twitterService.getTweetsByHashtag(request.getHashtag());

            LinkedList<String> payloadData = new LinkedList<>();

            for (Tweet tweet : tweetsByHashtag) {
                payloadData.add(tweet.getText());
            }

            return new Payload(request.getRequestId(), payloadData);
        } catch (TwitterServiceException e) {
            throw new AnalysisRequestException("Exception caught hashtag adapting request to payload.", e);
        }
    }
}
