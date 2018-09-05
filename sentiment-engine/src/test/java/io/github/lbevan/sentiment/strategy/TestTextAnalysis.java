package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.TextAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link TextAnalysis}.
 */
@ExtendWith(SpringExtension.class)
public class TestTextAnalysis {

    @MockBean
    private AnalysisRequestRepository analysisRequestRepository;

    @MockBean
    private AnalysisResultRepository analysisResultRepository;

    @Test
    public void whenRequestReceived_thenRequestIsProcessedAndResultIsReturned() {
        when(analysisRequestRepository.findByRequestId(any()))
                .thenReturn(mock(AnalysisRequestEntity.class));
        when(analysisRequestRepository.save(any()))
                .thenReturn(mock(AnalysisRequestEntity.class));

        TextAnalysisRequestDto request = new TextAnalysisRequestDto("This analysis engine is the best!");

        new TextAnalysis(analysisRequestRepository, analysisResultRepository)
                .receiveRequest(request);

        ArgumentCaptor<List<AnalysisResult>> captor = ArgumentCaptor.forClass(List.class);
        verify(analysisRequestRepository, times(2)).save(any(AnalysisRequestEntity.class));
        verify(analysisResultRepository, times(1)).saveAll(captor.capture());

        assertEquals(1, captor.getValue().size());
        assertTrue(captor.getValue().get(0).getSentiment().equals(Sentiment.VERY_POSITIVE.getDescription()));
    }
}
