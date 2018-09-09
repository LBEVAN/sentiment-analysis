package io.github.lbevan.sentiment.repository.document.conversion;

import io.github.lbevan.sentiment.service.domain.exception.AnalysisRequestException;

/**
 * Custom exception for document conversions.
 */
public class DocumentConversionException extends AnalysisRequestException {

    /**
     * Constructor.
     *
     * @param message the error message
     * @param throwable the original exception
     */
    public DocumentConversionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
