package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.domain.Tweet;
import io.github.lbevan.sentiment.domain.request.TweetAnalysisRequest;
import io.github.lbevan.sentiment.domain.result.AnalysisResult;
import io.github.lbevan.sentiment.domain.result.Sentiment;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.TwitterService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link TweetAnalysis}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBeanUtil.class })
public class TestTweetAnalysis {

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
    public void whenRequestReceived_thenRequestIsProcessedAndResultIsReturned() {
        TweetAnalysisRequest request = new TweetAnalysisRequest("1017825387785719808");

        List<AnalysisResult> analysisResult = new TweetAnalysis().receiveRequest(request);

        assertEquals(1, analysisResult.size());
        assertTrue(analysisResult.get(0).getSentiment().equals(Sentiment.POSITIVE.getDescription()));
    }
}
