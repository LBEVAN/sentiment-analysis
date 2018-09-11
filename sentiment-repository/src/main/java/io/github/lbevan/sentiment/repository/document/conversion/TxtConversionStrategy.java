package io.github.lbevan.sentiment.repository.document.conversion;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Document conversion strategy for .txt documents.
 */
public class TxtConversionStrategy implements DocumentConversionStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(final GridFsResource resource) throws DocumentConversionException {
        try {
            // would like to use lambda here but no functional interface
            ByteSource byteSource = new ByteSource() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public InputStream openStream() throws IOException {
                    return resource.getInputStream();
                }
            };

            return byteSource.asCharSource(Charsets.UTF_8).read();
        } catch (IOException e) {
           throw new DocumentConversionException("Exception converting a txt document to its String contents.", e);
        }
    }
}
