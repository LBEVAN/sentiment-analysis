package io.github.lbevan.sentiment.service.domain.exception;

/**
 * Common exception class for the services.
 */
public class AnalysisRequestException extends Exception {

    /**
     * Constructor.
     *
     * @param message custom message
     * @param throwable the exception
     */
    public AnalysisRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
