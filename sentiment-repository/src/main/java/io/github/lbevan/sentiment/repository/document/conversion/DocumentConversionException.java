package io.github.lbevan.sentiment.repository.document.conversion;

/**
 * Custom exception for document conversions.
 */
public class DocumentConversionException extends Exception {

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
