package io.github.lbevan.sentiment.service.domain.entity;

import io.github.lbevan.sentiment.service.domain.entity.Sentence;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Json deserializer for the {@link Sentence} class.
 */
public class SentenceDeserializer extends StdDeserializer<Sentence> {

    /**
     * Constructor.
     */
    public SentenceDeserializer() {
        this(null);
    }

    /**
     * Protected constructor.
     *
     * @param vc
     */
    protected SentenceDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sentence deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        String sentimentValue = rootNode.get("sentimentValue").asText();
        String sentiment = rootNode.get("sentiment").asText();
        String sentimentTree = rootNode.get("sentimentTree").asText();
        ObjectMapper objectMapper = new ObjectMapper();
        Float[] sentimentDistribution = objectMapper.readValue(rootNode.get("sentimentDistribution"), Float[].class);

        return new Sentence(sentimentValue, sentiment, sentimentTree, sentimentDistribution);
    }
}
