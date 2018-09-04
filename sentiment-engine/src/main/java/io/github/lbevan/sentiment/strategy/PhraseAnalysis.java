package io.github.lbevan.sentiment.strategy;

import io.github.lbevan.sentiment.pipeline.Pipeline;
import io.github.lbevan.sentiment.pipeline.adapter.PhrasePipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import io.github.lbevan.sentiment.repository.impl.AnalysisResultRepository;
import io.github.lbevan.sentiment.service.domain.dto.TextAnalysisRequestDto;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An {@link AnalysisRequestListener} implementation for a single phrase.
 * This accepts requests with the signature: {@link TextAnalysisRequestDto}.
 */
@Component
public class PhraseAnalysis implements AnalysisRequestListener<TextAnalysisRequestDto> {

    private final AnalysisResultRepository analysisResultRepository;

    @Autowired
    public PhraseAnalysis(AnalysisResultRepository analysisResultRepository) {
        this.analysisResultRepository = analysisResultRepository;
    }

    /**
     * Receive and process requests from the 'Phrase Analysis Request' queue.
     *
     * @param request the request to process.
     * @return List of analysis results.
     */
    @RabbitListener(queues = "${rabbitmq.queue.request.phrase}")
    @Override
    public void receiveRequest(TextAnalysisRequestDto request) {
        List<AnalysisResult> results = new Pipeline.PipelineBuilder()
                .adapt(new PhrasePipelineAdapter(request))
                .pipe(new AnalysisPipe())
                .build()
                .process();

        results = analysisResultRepository.saveAll(results);

        System.out.println(results);
    }
}
