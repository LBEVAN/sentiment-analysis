package io.github.lbevan.sentiment.domain.validation.tweet;

import io.github.lbevan.sentiment.service.domain.validation.tweet.TweetLinkValidator;
import io.github.lbevan.twitter.service.domain.Tweet;
import io.github.lbevan.twitter.service.exception.TwitterServiceException;
import io.github.lbevan.twitter.service.impl.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link io.github.lbevan.sentiment.service.domain.validation.tweet.TweetLinkValidator}
 */
public class TestTweetLinkValidation {

    private static final String EXAMPLE_ID = "1017825387785719808";

    private TwitterService twitterService;
    private TweetLinkValidator tweetLinkValidator;

    @BeforeEach
    public void setUp() {
        twitterService = mock(TwitterService.class);
        tweetLinkValidator = new TweetLinkValidator(twitterService);
    }

    @Test
    public void whenAValidTweetIsFoundOnTwitter_thenReturnTrue() throws TwitterServiceException {
        when(twitterService.getTweetById(EXAMPLE_ID))
                .thenReturn(new Tweet(Long.valueOf(EXAMPLE_ID), "text"));

        boolean result = tweetLinkValidator
                .isValid("/" + EXAMPLE_ID, mock(ConstraintValidatorContext.class));

        assertTrue(result);
    }

    @Test
    public void whenTweetNotFoundAndIsNull_thenReturnFalse() throws TwitterServiceException {
        when(twitterService.getTweetById(EXAMPLE_ID))
                .thenReturn(null);

        boolean result = tweetLinkValidator
                .isValid("/" + EXAMPLE_ID, mock(ConstraintValidatorContext.class));

        assertFalse(result);
    }

    @Test
    public void whenExceptionThrownDuringTwitterSearch_thenReturnFalse() throws TwitterServiceException {
        when(twitterService.getTweetById(EXAMPLE_ID))
                .thenThrow(mock(TwitterServiceException.class));

        boolean result = tweetLinkValidator
                .isValid("/1017825387785719808", mock(ConstraintValidatorContext.class));

        assertFalse(result);
    }
}
