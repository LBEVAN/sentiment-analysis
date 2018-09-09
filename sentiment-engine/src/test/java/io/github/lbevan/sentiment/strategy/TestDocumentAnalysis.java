package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.repository.document.conversion.DocumentConversionException;
import io.github.lbevan.sentiment.repository.document.conversion.DocumentConversionStrategy;
import io.github.lbevan.sentiment.repository.document.conversion.TxtConversionStrategy;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.repository.impl.DocumentRepository;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.DocumentAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.misc.DocumentType;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
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
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link DocumentAnalysis}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBeanUtil.class })
public class TestDocumentAnalysis {

    @Mock
    private ApplicationContext context;

    @Autowired
    private SpringBeanUtil springBeanUtil;

    @MockBean
    private AnalysisRequestRepository analysisRequestRepository;

    @MockBean
    private AnalysisResultRepository analysisResultRepository;

    @MockBean
    private DocumentRepository documentRepository;

    @BeforeEach
    private void setUp() throws TwitterServiceException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(context.getBean(DocumentRepository.class)).thenReturn(documentRepository);
        Mockito.when(documentRepository.findById("1097213231"))
                .thenReturn(mock(GridFsResource.class));
        springBeanUtil.setApplicationContext(context);
    }

    @Test
    public void whenTxtDocumentRequestReceived_thenRequestIsProcessedAndResultIsReturned() throws DocumentConversionException {
        DocumentConversionStrategy documentConversionStrategy = mock(TxtConversionStrategy.class);
        when(documentConversionStrategy.convert(any())).thenReturn("My document contents.");
        when(analysisRequestRepository.findByRequestId(any()))
                .thenReturn(mock(AnalysisRequestEntity.class));
        when(analysisRequestRepository.save(any()))
                .thenReturn(mock(AnalysisRequestEntity.class));
        when(documentRepository.getDocumentConversionStrategyForDocument(any()))
                .thenReturn(documentConversionStrategy);

        DocumentAnalysisRequestDto request
                = new DocumentAnalysisRequestDto("E876s-EWAsdabsa", "1097213231", DocumentType.TXT);

        new DocumentAnalysis(analysisRequestRepository, analysisResultRepository, documentRepository)
                .receiveRequest(request);

        ArgumentCaptor<List<AnalysisResult>> captor = ArgumentCaptor.forClass(List.class);
        verify(analysisRequestRepository, times(2)).save(any(AnalysisRequestEntity.class));
        verify(analysisResultRepository, times(1)).saveAll(captor.capture());
        verify(documentConversionStrategy, times(1)).convert(any(GridFsResource.class));

        assertEquals(1, captor.getValue().size());
    }
}
