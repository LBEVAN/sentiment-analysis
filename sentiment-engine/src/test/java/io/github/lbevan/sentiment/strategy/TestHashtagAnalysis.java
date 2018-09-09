package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.repository.document.conversion.DocumentConversionException;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.HashtagAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link HashtagAnalysis}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBeanUtil.class })
public class TestHashtagAnalysis {

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
        when(twitterService.getTweetsByHashtag("#example"))
                .thenReturn(getExampleListOfTweets());

        HashtagAnalysisRequestDto request = new HashtagAnalysisRequestDto("#example");

        new HashtagAnalysis(analysisRequestRepository, analysisResultRepository)
                .receiveRequest(request);

        ArgumentCaptor<List<AnalysisResult>> captor = ArgumentCaptor.forClass(List.class);
        verify(analysisRequestRepository, times(2)).save(any(AnalysisRequestEntity.class));
        verify(analysisResultRepository, times(1)).saveAll(captor.capture());

        assertEquals(7, captor.getValue().size());
    }

    @Test
    public void whenExceptionThrown_thenRequestSetToFailed() throws TwitterServiceException {
        when(twitterService.getTweetsByHashtag("#example"))
                .thenThrow(mock(TwitterServiceException.class));

        HashtagAnalysisRequestDto request = new HashtagAnalysisRequestDto("#example");

        new HashtagAnalysis(analysisRequestRepository, analysisResultRepository)
                .receiveRequest(request);

        ArgumentCaptor<AnalysisRequestEntity> captor = ArgumentCaptor.forClass(AnalysisRequestEntity.class);
        verify(analysisRequestRepository, times(2)).save(captor.capture());
        verify(captor.getAllValues().get(1)).setStatus(RequestStatus.FAILED);
    }

    private List<Tweet> getExampleListOfTweets() {
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(new Tweet(new Long(000001), "I love Monday mornings!"));
        tweets.add(new Tweet(new Long(000002), "One day I will be a mushroom"));
        tweets.add(new Tweet(new Long(000003), "The sun hurts my eyes"));
        tweets.add(new Tweet(new Long(000004), "I wish I could og to space"));
        tweets.add(new Tweet(new Long(000005), "Rain is not fun."));
        tweets.add(new Tweet(new Long(000006), "Tuesdays are cool."));
        tweets.add(new Tweet(new Long(000007), "Turtles are cute."));
        return tweets;
    }
}
