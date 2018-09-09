package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link TweetAnalysis}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringBeanUtil.class})
public class TestTweetAnalysis {

    @Mock
    private ApplicationContext context;

    @Mock
    private TwitterService twitterService;

    @Autowired
    private SpringBeanUtil springBeanUtil;

    @MockBean
    private AnalysisRequestRepository analysisRequestRepository;

    @MockBean
    private AnalysisResultRepository analysisResultRepository;

    @BeforeEach
    private void setUp() throws TwitterServiceException {
        MockitoAnnotations.initMocks(this);
        when(context.getBean(TwitterService.class)).thenReturn(twitterService);

        springBeanUtil.setApplicationContext(context);

        when(analysisRequestRepository.findByRequestId(any()))
                .thenReturn(mock(AnalysisRequestEntity.class));
        when(analysisRequestRepository.save(any()))
                .thenReturn(mock(AnalysisRequestEntity.class));
    }

    @Test
    public void whenRequestReceived_thenRequestIsProcessedAndResultIsReturned() throws TwitterServiceException {
        when(twitterService.getTweetById("1017825387785719808"))
                .thenReturn(new Tweet(new Long(000001), "I love Monday mornings!"));

        TweetAnalysisRequestDto request = new TweetAnalysisRequestDto("1017825387785719808");

        new TweetAnalysis(analysisRequestRepository, analysisResultRepository).receiveRequest(request);

        ArgumentCaptor<List<AnalysisResult>> captor = ArgumentCaptor.forClass(List.class);
        verify(analysisResultRepository).saveAll(captor.capture());

        assertEquals(1, captor.getValue().size());
        assertTrue(captor.getValue().get(0).getSentiment().equals(Sentiment.POSITIVE.getDescription()));
    }

    @Test
    public void whenExceptionThrown_thenRequestSetToFailed() throws TwitterServiceException {
        when(twitterService.getTweetById("1017825387785719808"))
                .thenThrow(mock(TwitterServiceException.class));

        TweetAnalysisRequestDto request = new TweetAnalysisRequestDto("1017825387785719808");

        new TweetAnalysis(analysisRequestRepository, analysisResultRepository).receiveRequest(request);

        ArgumentCaptor<AnalysisRequestEntity> captor = ArgumentCaptor.forClass(AnalysisRequestEntity.class);
        verify(analysisRequestRepository, times(2)).save(captor.capture());
        verify(captor.getAllValues().get(1)).setStatus(RequestStatus.FAILED);
    }
}
