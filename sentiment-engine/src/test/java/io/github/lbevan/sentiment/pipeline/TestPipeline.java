package io.github.lbevan.sentiment.pipeline;

import io.github.lbevan.sentiment.domain.request.PhraseAnalysisRequest;
import io.github.lbevan.sentiment.domain.result.AnalysisResult;
import io.github.lbevan.sentiment.pipeline.adapter.PhrasePipelineAdapter;
import io.github.lbevan.sentiment.pipeline.adapter.PipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.AnalysisPipe;
import io.github.lbevan.sentiment.pipeline.pipe.Pipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@link Pipeline}.
 */
public class TestPipeline {

    private PipelineAdapter pipelineAdapter;
    private Pipe pipe;

    @BeforeEach
    public void setUp() {
        pipelineAdapter = mock(PipelineAdapter.class);
        pipe = mock(Pipe.class);
    }

    @Test
    public void whenPipelineIsBuilt_thenNoExceptionsAreThrown() {
        try {
            new Pipeline.PipelineBuilder()
                    .adapt(pipelineAdapter)
                    .pipe(pipe)
                    .build();
        } catch (Exception e) {
            fail("This test threw an exception which it shouldn't have!");
        }
    }

    @Test
    public void whenPipelineIsUsedForProcessing_thenTheRequestIsProcessedAndResultIsReturned() {
        try {
            PhraseAnalysisRequest request = new PhraseAnalysisRequest("This is my 1st sentence. This is my second!");

            List<AnalysisResult> results = new Pipeline.PipelineBuilder()
                    .adapt(new PhrasePipelineAdapter(request))
                    .pipe(new AnalysisPipe())
                    .build()
                    .process();

            assertEquals(1, results.size());
            assertEquals(2, results.get(0).getSentences().size());
        } catch (Exception e) {
            fail("This test threw an exception which it shouldn't have!");
        }
    }

    @Test
    public void whenMultipleAdaptersAreAddedToThePipeline_thenAnExceptionIsThrown() {
        try {
            new Pipeline.PipelineBuilder()
                    .adapt(pipelineAdapter)
                    .adapt(pipelineAdapter)
                    .build();

            fail("This scenario should have thrown an IllegalStateException!");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }

    @Test
    public void whenPipelineIsBuiltWithoutAnAdapter_thenAnExceptionIsThrown() {
        try {
            new Pipeline.PipelineBuilder()
                    .pipe(pipe)
                    .build();

            fail("This scenario should have thrown an IllegalStateException!");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }
}
