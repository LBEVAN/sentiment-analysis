package io.github.lbevan.sentiment.repository.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

/**
 * MongoDB event listener.
 * Invoked a callback operation to save the DBref fields.
 */
public class CascadeSaveEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoOperations));
    }
}
