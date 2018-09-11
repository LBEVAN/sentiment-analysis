package io.github.lbevan.sentiment.repository.event;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Generic field callback class.
 */
public class FieldCallback implements ReflectionUtils.FieldCallback {

    private boolean idFound;

    /**
     * {@inheritDoc}
     */
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }
    }

    /**
     * Retrieve the indicator to whether the Id field was found.
     *
     * @return boolean idFound
     */
    public boolean isIdFound() {
        return idFound;
    }
}
