package io.github.lbevan.sentiment.pipeline.pipe;

import io.github.lbevan.sentiment.pipeline.Payload;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link RemoveRetweetPipe}.
 */
public class TestRemoveRetweetPipe {

    @Test
    public void whenTextExistsWithARetweet_thenItIsRemoved() {
        String textText = "This bit of text has a RT in the middle";

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(textText);

        new RemoveRetweetPipe().process(new Payload("001", payloadData));

        assertEquals("This bit of text has a in the middle", payloadData.get(0));
    }

    @Test
    public void whenTextExistsWithMultipleRetweets_thenTheyAreRemoved() {
        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add("This RT has multiple RT in the sentence RT RT RT");

        new RemoveRetweetPipe().process(new Payload("001", payloadData));

        assertEquals("This has multiple in the sentence", payloadData.get(0));
    }

    @Test
    public void whenTextExistsRetweetsAtStartMiddleAndEnd_thenTheyAreRemoved() {
        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add("RT at the start");
        payloadData.add("Now the RT is in the middle");
        payloadData.add("Now its at the end RT");

        new RemoveRetweetPipe().process(new Payload("001", payloadData));

        assertEquals("at the start", payloadData.get(0));
        assertEquals("Now the is in the middle", payloadData.get(1));
        assertEquals("Now its at the end", payloadData.get(2));
    }

    @Test
    public void whenTextDoesNotContainARetweet_thenTheOriginalTextIsMaintained() {
        String textText = "This bit of text does not have aforementioned in the middle";

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(textText);

        new RemoveRetweetPipe().process(new Payload("001", payloadData));

        assertEquals(textText, payloadData.get(0));
    }
}
