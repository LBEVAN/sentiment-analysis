package io.github.lbevan.sentiment.service.domain.validation.hashtag;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Hashtag validation annotation.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Constraint(validatedBy = HashtagValidator.class)
public @interface Hashtag {

    String message() default "Must be a valid hashtag";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
