package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.TweetPipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.TweetAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for a single tweet.
 * This accepts requests with the signature: {@link TweetAnalysisRequest}.
 */
@Component
public class TweetAnalysis implements AnalysisRequestListener<TweetAnalysisRequest> {

    private final AnalysisResultRepository analysisResultRepository;

    @Autowired
    public TweetAnalysis(AnalysisResultRepository analysisResultRepository) {
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
    public void receiveRequest(TweetAnalysisRequest request) {
        List<AnalysisResult> results = new Pipeline.PipelineBuilder()
                .adapt(new TweetPipelineAdapter(request))
                .pipe(new AnalysisPipe())
                .build()
                .process();

        analysisResultRepository.saveAll(results);
    }
}
