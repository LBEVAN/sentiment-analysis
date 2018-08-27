package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.domain.request.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.domain.request.TweetAnalysisRequest;
import io.github.lbevan.sentiment.domain.result.AnalysisResult;
import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.TweetPipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for a single tweet.
 * This accepts requests with the signature: {@link PhraseAnalysisRequest}.
 */
@Component
public class TweetAnalysis implements AnalysisRequestListener<TweetAnalysisRequest> {

    /**
     * Receive and process requests from the 'Tweet Analysis Request' queue.
     *
     * @param request the request to process.
     * @return List of analysis results.
     */
    @RabbitListener
    @Override
    public List<AnalysisResult> receiveRequest(TweetAnalysisRequest request) {
        return new Pipeline.PipelineBuilder()
                .adapt(new TweetPipelineAdapter(request))
                .pipe(new AnalysisPipe())
                .build()
                .process();
    }
}
