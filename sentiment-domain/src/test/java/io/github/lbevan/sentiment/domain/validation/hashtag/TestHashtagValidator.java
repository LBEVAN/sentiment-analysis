package io.github.lbevan.sentiment.domain.validation.hashtag;

import io.github.lbevan.sentiment.service.domain.validation.hashtag.HashtagValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@link io.github.lbevan.sentiment.service.domain.validation.hashtag.HashtagValidator}
 */
public class TestHashtagValidator {

    @Test
    public void whenValidHashtagEntered_thenReturnTrue() {
        boolean result = new HashtagValidator()
                .isValid("#example", mock(ConstraintValidatorContext.class));
        assertTrue(result);
    }

    @Test
    public void whenStringWithNoHashEntered_thenReturnFalse() {
        boolean result = new HashtagValidator()
                .isValid("example", mock(ConstraintValidatorContext.class));
        assertFalse(result);
    }

    @Test
    public void whenStringWithSpacesEntered_thenReturnFalse() {
        boolean result = new HashtagValidator()
                .isValid("#example  more here", mock(ConstraintValidatorContext.class));
        assertFalse(result);
    }
}
