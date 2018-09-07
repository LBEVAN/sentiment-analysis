package io.github.lbevan.sentiment.util.document;

import org.springframework.data.mongodb.gridfs.GridFsResource;

public interface DocumentConversionStrategy {

    /**
     * Convert the given document into a String of its contents.
     *
     * @param resource the document
     * @return String
     * @throws DocumentConversionException
     */
    String convert(GridFsResource resource) throws DocumentConversionException;
}
