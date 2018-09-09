package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.exception.AnalysisRequestException;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link TweetPipelineAdapter}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringBeanUtil.class})
public class TestTweetPipelineAdapter {

    @Mock
    private ApplicationContext context;

    @Mock
    private TwitterService twitterService;

    @Autowired
    private SpringBeanUtil springBeanUtil;

    @BeforeEach
    private void setUp() throws TwitterServiceException {
        MockitoAnnotations.initMocks(this);
        when(context.getBean(TwitterService.class)).thenReturn(twitterService);
        springBeanUtil.setApplicationContext(context);
    }

    @Test
    public void whenAdapterExecuted_thenRequestProcessedIntoPayload() throws AnalysisRequestException, TwitterServiceException {
        when(twitterService.getTweetById("1017825387785719808"))
                .thenReturn(new Tweet(new Long(000001), "I love Monday mornings!"));

        TweetAnalysisRequestDto request = new TweetAnalysisRequestDto("1017825387785719808");
        TweetPipelineAdapter adapter = new TweetPipelineAdapter(request);

        Payload payload = adapter.adapt();

        assertEquals(1, payload.getInput().size());
    }

    @Test
    public void whenServiceThrowsException_thenAdapterHandlesAndThrowsCorrectException() throws TwitterServiceException {
        when(twitterService.getTweetById("1017825387785719808"))
                .thenThrow(mock(TwitterServiceException.class));

        TweetAnalysisRequestDto request = new TweetAnalysisRequestDto("1017825387785719808");
        TweetPipelineAdapter adapter = new TweetPipelineAdapter(request);

        try {
            Payload payload = adapter.adapt();
            fail();
        } catch (AnalysisRequestException e) {
            assertTrue(true);
            assertTrue(e.getCause() instanceof TwitterServiceException);
        }
    }
}
