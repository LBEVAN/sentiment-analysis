package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.domain.dto.PhraseAnalysisRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link PhrasePipelineAdapter}.
 */
public class TestPhrasePipelineAdapter {

    @Test
    public void whenAdapterExecuted_thenRequestProcessedIntoPayload() {
        PhraseAnalysisRequest request = new PhraseAnalysisRequest("My phrase");
        PhrasePipelineAdapter adapter = new PhrasePipelineAdapter(request);

        Payload payload = adapter.adapt();

        assertEquals(1, payload.getInput().size());
    }

    // todo: add tests around validation?
}
