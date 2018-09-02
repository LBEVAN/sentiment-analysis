package io.github.lbevan.sentiment.pipeline;

import io.github.lbevan.sentiment.service.domain.entity.AnalysisResult;
import io.github.lbevan.sentiment.pipeline.adapter.PipelineAdapter;
import io.github.lbevan.sentiment.pipeline.pipe.Pipe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Modular analysis pipeline providing the ability to add varying pipes to process the input data.
 */
public final class Pipeline {

    private PipelineAdapter pipelineAdapter;

    private Deque<Pipe> queue = new ArrayDeque<>();

    /**
     * Private constructor.
     */
    private Pipeline() { }

    /**
     * Process the input data and produce an output.
     *
     * @return List of analysis results
     */
    public List<AnalysisResult> process() {
        Payload payload = pipelineAdapter.adapt();

        for(Pipe pipe : queue) {
            pipe.process(payload);
        }

        return payload.getResults();
    }

    /**
     * Builder for the pipeline.
     */
    public static class PipelineBuilder {

        private Pipeline pipeline;

        /**
         * Constructor.
         */
        public PipelineBuilder() {
            this.pipeline = new Pipeline();
        }

        /**
         * Add an adapter to the pipeline.
         * The action of this is applied before any pipe.
         *
         * @param pipelineAdapter the adapter to add
         * @return PipelineBuilder
         */
        public PipelineBuilder adapt(PipelineAdapter pipelineAdapter) {
            if(pipeline.pipelineAdapter != null) {
                throw new IllegalStateException("An PipelineAdapter has already been added to the pipeline!");
            }
            pipeline.pipelineAdapter = pipelineAdapter;
            return this;
        }

        /**
         * Add a pipe to the pipeline.
         *
         * @param pipe the pipe to add
         * @return PipelineBuilder
         */
        public PipelineBuilder pipe(Pipe pipe) {
            pipeline.queue.offer(pipe);
            return this;
        }

        /**
         * Build the pipeline with the pipes added previously.
         *
         * @return Pipeline
         */
        public Pipeline build() {
            if(pipeline.pipelineAdapter == null) {
                throw new IllegalStateException("The pipeline must contain a PipelineAdapter!");
            }
            return pipeline;
        }
    }
}
