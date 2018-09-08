package io.github.lbevan.sentiment.service.domain.validation.tweet;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Tweet link validation annotation.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Constraint(validatedBy = TweetLinkValidator.class)
public @interface TweetLink {

    String message() default "Must be a valid Tweet link";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
