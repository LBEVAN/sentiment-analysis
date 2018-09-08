package io.github.lbevan.twitter.service.exception;

/**
 * Custom exception the Twitter service.
 */
public class TwitterServiceException extends Exception {

    /**
     * Constructor.
     *
     * @param message the error message
     * @param throwable the original exception
     */
    public TwitterServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
