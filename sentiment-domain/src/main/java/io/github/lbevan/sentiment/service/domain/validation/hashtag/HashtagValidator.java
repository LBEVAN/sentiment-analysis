package io.github.lbevan.sentiment.service.domain.validation.hashtag;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *  * Constraint validator for validating hashtags.
 */
@Component
public class HashtagValidator implements ConstraintValidator<Hashtag, String> {

    /**
     * Determine if the supplied hashtag is valid.
     *
     * @param hashtag hashtag to check
     * @param constraintValidatorContext context
     * @return boolean true if valid, otherwise false
     */
    @Override
    public boolean isValid(String hashtag, ConstraintValidatorContext constraintValidatorContext) {
        // ensure it starts with a hash and does not contain any spaces (which may indicate more data)
        return hashtag.startsWith("#") && !hashtag.trim().contains(" ");
    }
}
