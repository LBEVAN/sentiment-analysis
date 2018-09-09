package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.HashtagPipelineAdapter;
import io.github.lbevan.sentiment.pipeline.adapter.TweetPipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import io.github.lbevan.sentiment.repository.impl.AnalysisRequestRepository;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.HashtagAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for multiple tweets with a specified hashtag.
 * This accepts requests with the signature: {@link HashtagAnalysisRequestDto}.
 */
@Component
public class HashtagAnalysis implements AnalysisRequestListener<HashtagAnalysisRequestDto> {

    private final AnalysisRequestRepository analysisRequestRepository;
    private final AnalysisResultRepository analysisResultRepository;

    @Autowired
    public HashtagAnalysis(AnalysisRequestRepository analysisRequestRepository,
                           AnalysisResultRepository analysisResultRepository) {
        this.analysisRequestRepository = analysisRequestRepository;
        this.analysisResultRepository = analysisResultRepository;
    }

    /**
     * Receive and process requests from the 'Hashtag Analysis Request' queue.
     *
     * @param request the request to process.
     * @return List of analysis results.
     */
    @RabbitListener(queues = "${rabbitmq.queue.request.hashtag}")
    @Override
    public void receiveRequest(HashtagAnalysisRequestDto request) {
        // update the request status to reflect processing
        AnalysisRequestEntity entity = analysisRequestRepository.findByRequestId(request.getRequestId());
        entity.setStatus(RequestStatus.IN_PROGRESS);
        entity = analysisRequestRepository.save(entity);

        List<AnalysisResult> results = new Pipeline.PipelineBuilder()
                .adapt(new HashtagPipelineAdapter(request))
                .pipe(new AnalysisPipe())
                .build()
                .process();

        // save the results and update the entity status to complete
        analysisResultRepository.saveAll(results);
        entity.setStatus(RequestStatus.COMPLETE);
        analysisRequestRepository.save(entity);
    }
}
