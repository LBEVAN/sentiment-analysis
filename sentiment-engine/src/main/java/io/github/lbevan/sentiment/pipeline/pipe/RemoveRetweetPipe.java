package io.github.lbevan.sentiment.pipeline.pipe;

import io.github.lbevan.sentiment.pipeline.Payload;

import java.util.LinkedList;

public class RemoveRetweetPipe implements Pipe {

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(Payload payload) {
        LinkedList<String> inputs = payload.getInput();

        for(int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);

            // replace RT
            input = input.replaceAll("RT", "");

            // clean up by replacing any double spaces
            input = input.replaceAll("\\s{2,}", " ").trim();

            // set back to list
            inputs.set(i, input);
        }
    }
}
