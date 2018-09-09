package io.github.lbevan.sentiment.pipeline.pipe;

import io.github.lbevan.sentiment.pipeline.Payload;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link RemoveHashtagPipe}.
 */
public class TestRemoveHashtagPipe {

    @Test
    public void whenTextExistsWithAHashtag_thenItIsRemoved() {
        String textText = "This bit of text has a #example in the middle";

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(textText);

        new RemoveHashtagPipe().process(new Payload("001", payloadData));

        assertEquals("This bit of text has a in the middle", payloadData.get(0));
    }

    @Test
    public void whenTextExistsWithMultipleHashtags_thenTheyAreRemoved() {
        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add("This #example0 has multiple #example1 in the sentence #example #me #notme");

        new RemoveHashtagPipe().process(new Payload("001", payloadData));

        assertEquals("This has multiple in the sentence", payloadData.get(0));
    }

    @Test
    public void whenTextExistsHashtagsAtStartMiddleAndEnd_thenTheyAreRemoved() {
        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add("#tag at the start");
        payloadData.add("Now the #sdadas is in the middle");
        payloadData.add("Now its at the end #tea");

        new RemoveHashtagPipe().process(new Payload("001", payloadData));

        assertEquals("at the start", payloadData.get(0));
        assertEquals("Now the is in the middle", payloadData.get(1));
        assertEquals("Now its at the end", payloadData.get(2));
    }

    @Test
    public void whenTextDoesNotContainAHashtag_thenTheOriginalTextIsMaintained() {
        String textText = "This bit of text does not have aforementioned in the middle";

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(textText);

        new RemoveHashtagPipe().process(new Payload("001", payloadData));

        assertEquals(textText, payloadData.get(0));
    }
}
