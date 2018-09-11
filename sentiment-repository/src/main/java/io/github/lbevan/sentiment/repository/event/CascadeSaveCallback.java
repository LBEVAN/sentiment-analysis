package io.github.lbevan.sentiment.repository.event;


import io.github.lbevan.sentiment.service.domain.annotation.CascadeSave;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Callback operations for cascading save operations in MongoDB.
 */
public class CascadeSaveCallback implements ReflectionUtils.FieldCallback {

    private Object source;
    private MongoOperations mongoOperations;

    /**
     * Constructor.
     *
     * @param source the source object
     * @param mongoOperations service to perform db operations
     */
    CascadeSaveCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.mongoOperations = mongoOperations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(DBRef.class)
                && field.isAnnotationPresent(CascadeSave.class)) {
            final Object fieldValue = field.get(getSource());

            if (fieldValue != null) {
                final FieldCallback callback = new FieldCallback();

                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);

                if(fieldValue instanceof Collection) {
                    // if collection, iterate over and save each object
                    for (Object object : (Collection) fieldValue) {
                        getMongoOperations().save(object);
                    }
                } else {
                    // single value, save it
                    getMongoOperations().save(fieldValue);
                }
            }
        }

    }

    /**
     * Retrieve the source object.
     *
     * @return Object source
     */
    private Object getSource() {
        return source;
    }

    /**
     * Retrieve ethe mongo service.
     *
     * @return MongoOperations service
     */
    private MongoOperations getMongoOperations() {
        return mongoOperations;
    }
}
