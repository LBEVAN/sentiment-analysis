package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    private AnalysisResultRepository analysisResultRepository;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(context.getBean(TwitterService.class)).thenReturn(twitterService);
        Mockito.when(twitterService.getTweetById("1017825387785719808"))
                .thenReturn(new Tweet(new Long(000001), "I love Monday mornings!"));
        springBeanUtil.setApplicationContext(context);
        analysisResultRepository = mock(AnalysisResultRepository.class);
    }

    @Test
    public void whenRequestReceived_thenRequestIsProcessedAndResultIsReturned() {
        TweetAnalysisRequestDto request = new TweetAnalysisRequestDto("1017825387785719808");

        new TweetAnalysis(analysisResultRepository).receiveRequest(request);

        ArgumentCaptor<List<AnalysisResult>> captor = ArgumentCaptor.forClass(List.class);
        verify(analysisResultRepository).saveAll(captor.capture());

        assertEquals(1, captor.getValue().size());
        assertTrue(captor.getValue().get(0).getSentiment().equals(Sentiment.POSITIVE.getDescription()));
    }
}
