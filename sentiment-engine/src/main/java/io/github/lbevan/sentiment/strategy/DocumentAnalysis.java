package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.DocumentPipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.repository.impl.DocumentRepository;
import io.github.lbevan.sentiment.service.domain.dto.DocumentAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for a single document.
 * This accepts requests with the signature: {@link DocumentAnalysisRequestDto}.
 */
@Component
public class DocumentAnalysis implements AnalysisRequestListener<DocumentAnalysisRequestDto> {

    private final AnalysisRequestRepository analysisRequestRepository;
    private final AnalysisResultRepository analysisResultRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentAnalysis(AnalysisRequestRepository analysisRequestRepository,
                            AnalysisResultRepository analysisResultRepository,
                            DocumentRepository documentRepository) {
        this.analysisRequestRepository = analysisRequestRepository;
        this.analysisResultRepository = analysisResultRepository;
        this.documentRepository = documentRepository;
    }

    /**
     * Receive and process requests from the 'Document Analysis Request' queue.
     *
     * @param request the request to process.
     * @return List of analysis results.
     */
    @RabbitListener(queues = "${rabbitmq.queue.request.document}")
    @Override
    public void receiveRequest(DocumentAnalysisRequestDto request) {
        // update the request status to reflect processing
        AnalysisRequestEntity entity = analysisRequestRepository.findByRequestId(request.getRequestId());
        entity.setStatus(RequestStatus.IN_PROGRESS);
        entity = analysisRequestRepository.save(entity);

        List<AnalysisResult> results = new Pipeline.PipelineBuilder()
                .adapt(new DocumentPipelineAdapter(request))
                .pipe(new AnalysisPipe())
                .build()
                .process();

        // save the results and update the entity status to complete
        analysisResultRepository.saveAll(results);
        entity.setStatus(RequestStatus.COMPLETE);
        analysisRequestRepository.save(entity);
    }
}
