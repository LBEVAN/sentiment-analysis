package io.github.lbevan.sentiment.repository.document.conversion;

import io.github.lbevan.sentiment.service.domain.misc.DocumentType;

/**
 * Factory for creating the appropriate conversion strategy for teh specified document type.
 */
public class DocumentConversionStrategyFactory {

    /**
     * Retrieve the conversion strategy based on the document type.
     * If the type is not found, an IllegalArgumentException is thrown.
     *
     * @param documentType the document type
     * @return DocumentConversionStrategy
     */
    public static DocumentConversionStrategy getConversionStrategy(DocumentType documentType) {
        switch(documentType) {
            case MS_WORD:
                return new MSWordConversionStrategy();
            case TXT:
                return new TxtConversionStrategy();
        }

        throw new IllegalArgumentException();
    }
}
