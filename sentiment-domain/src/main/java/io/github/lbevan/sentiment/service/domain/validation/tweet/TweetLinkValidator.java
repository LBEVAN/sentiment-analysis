package io.github.lbevan.sentiment.service.domain.validation.tweet;

import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Constraint validator for validating tweet links.
 * This searches twitter to ensure it is valid.
 */
@Component
public class TweetLinkValidator implements ConstraintValidator<TweetLink, String> {

    private TwitterService twitterService;

    /**
     * Constructor.
     *
     * @param twitterService
     */
    @Autowired
    public TweetLinkValidator(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    /**
     * Determine if the supplied tweet link is valid.
     * Search twitter to do this.
     *
     * @param tweetLink the link to check
     * @param constraintValidatorContext context
     * @return true if valid, otherwise false
     */
    @Override
    public boolean isValid(String tweetLink, ConstraintValidatorContext constraintValidatorContext) {
        String id = tweetLink.substring(tweetLink.lastIndexOf("/") + 1).trim();

        Tweet tweet;
        try {
            tweet = twitterService.getTweetById(id);
        } catch (TwitterServiceException e) {
            return false;
        }

        return tweet != null && tweet.getId() != null;
    }
}
