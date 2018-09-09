package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.TweetPipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.exception.AnalysisRequestException;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for a single tweet.
 * This accepts requests with the signature: {@link TweetAnalysisRequestDto}.
 */
@Component
public class TweetAnalysis implements AnalysisRequestListener<TweetAnalysisRequestDto> {

    private final AnalysisRequestRepository analysisRequestRepository;
    private final AnalysisResultRepository analysisResultRepository;

    @Autowired
    public TweetAnalysis(AnalysisRequestRepository analysisRequestRepository,
                         AnalysisResultRepository analysisResultRepository) {
        this.analysisRequestRepository = analysisRequestRepository;
        this.analysisResultRepository = analysisResultRepository;
    }

    /**
     * Receive and process requests from the 'Tweet Analysis Request' queue.
     *
     * @param request the request to process.
     * @return List of analysis results.
     */
    @RabbitListener(queues = "${rabbitmq.queue.request.tweet}")
    @Override
    public void receiveRequest(TweetAnalysisRequestDto request) {
        // update the request status to reflect processing
        AnalysisRequestEntity entity = analysisRequestRepository.findByRequestId(request.getRequestId());
        entity.setStatus(RequestStatus.IN_PROGRESS);
        entity = analysisRequestRepository.save(entity);

        List<AnalysisResult> results = null;
        try {
             results = new Pipeline.PipelineBuilder()
                    .adapt(new TweetPipelineAdapter(request))
                    .pipe(new AnalysisPipe())
                    .build()
                    .process();
        } catch (AnalysisRequestException e) {
            // analysis has failed, so set the status to failed
            entity.setStatus(RequestStatus.FAILED);
            analysisRequestRepository.save(entity);
            return;
        }

        // save the results and update the entity status to complete
        analysisResultRepository.saveAll(results);
        entity.setStatus(RequestStatus.COMPLETE);
        analysisRequestRepository.save(entity);
    }
}
