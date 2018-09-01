package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.service.domain.request.TweetAnalysisRequest;
import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link TweetPipelineAdapter}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBeanUtil.class })
public class TestTweetPipelineAdapter {

    @Mock
    private ApplicationContext context;

    @Mock
    private TwitterService twitterService;

    @Autowired
    private SpringBeanUtil springBeanUtil;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(context.getBean(TwitterService.class)).thenReturn(twitterService);
        Mockito.when(twitterService.getTweetById("1017825387785719808"))
                .thenReturn(new Tweet(new Long(000001), "I love Monday mornings!"));
        springBeanUtil.setApplicationContext(context);
    }

    @Test
    public void whenAdapterExecuted_thenRequestProcessedIntoPayload() {
        TweetAnalysisRequest request = new TweetAnalysisRequest("1017825387785719808");
        TweetPipelineAdapter adapter = new TweetPipelineAdapter(request);

        Payload payload = adapter.adapt();

        assertEquals(1, payload.getInput().size());
    }

    // todo: add tests around validation?
}
