package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.service.domain.request.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.service.domain.result.AnalysisResult;
import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.PhrasePipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for a single phrase.
 * This accepts requests with the signature: {@link PhraseAnalysisRequest}.
 */
@Component
public class PhraseAnalysis implements AnalysisRequestListener<PhraseAnalysisRequest> {

    /**
     * Receive and process requests from the 'Phrase Analysis Request' queue.
     *
     * @param request the request to process.
     * @return List of analysis results.
     */
    @RabbitListener
    @Override
    public List<AnalysisResult> receiveRequest(PhraseAnalysisRequest request) {
        return new Pipeline.PipelineBuilder()
                .adapt(new PhrasePipelineAdapter(request))
                .pipe(new AnalysisPipe())
                .build()
                .process();
    }
}
