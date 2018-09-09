package io.github.lbevan.sentiment.pipeline.pipe;

import io.github.lbevan.sentiment.pipeline.Payload;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveHashtagPipe implements Pipe {

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(Payload payload) {
        LinkedList<String> inputs = payload.getInput();

        for(int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);

            // create the matcher
            Pattern hashtagPattern = Pattern.compile("#(\\w+)");
            Matcher matcher = hashtagPattern.matcher(input);

            // find each occurrence and replace
            while (matcher.find()) {
                String match = matcher.group(1);
                input = input.replace("#" + match, "");
            }

            // clean up by replacing any double spaces
            input = input.replaceAll("\\s{2,}", " ").trim();

            // set back to list
            inputs.set(i, input);
        }
    }
}
