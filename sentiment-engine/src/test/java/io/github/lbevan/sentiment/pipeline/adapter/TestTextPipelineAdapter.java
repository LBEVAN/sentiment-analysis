package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.service.domain.dto.TextAnalysisRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link TextPipelineAdapter}.
 */
public class TestTextPipelineAdapter {

    @Test
    public void whenAdapterExecuted_thenRequestProcessedIntoPayload() {
        TextAnalysisRequestDto request = new TextAnalysisRequestDto("My phrase");
        TextPipelineAdapter adapter = new TextPipelineAdapter(request);

        Payload payload = adapter.adapt();

        assertEquals(1, payload.getInput().size());
    }
}
