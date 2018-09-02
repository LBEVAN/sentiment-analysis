package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.result.Sentiment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link PhraseAnalysis}.
 */
public class TestPhraseAnalysis {

    private AnalysisResultRepository analysisResultRepository;

    @BeforeEach
    public void setUp() {
        analysisResultRepository = mock(AnalysisResultRepository.class);
    }

    @Test
    public void whenRequestReceived_thenRequestIsProcessedAndResultIsReturned() {
        PhraseAnalysisRequest request = new PhraseAnalysisRequest("This analysis engine is the best!");

        new PhraseAnalysis(analysisResultRepository).receiveRequest(request);

        ArgumentCaptor<List<AnalysisResult>> captor = ArgumentCaptor.forClass(List.class);
        verify(analysisResultRepository).saveAll(captor.capture());

        assertEquals(1, captor.getValue().size());
        assertTrue(captor.getValue().get(0).getSentiment().equals(Sentiment.VERY_POSITIVE.getDescription()));
    }
}
