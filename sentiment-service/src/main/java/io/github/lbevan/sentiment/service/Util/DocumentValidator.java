package io.github.lbevan.sentiment.service.Util;

import io.github.lbevan.sentiment.service.domain.misc.DocumentType;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class that provides validation methods for MultipartFiles.
 */
public class DocumentValidator {

    /**
     * Determine if the given MultipartFile has a content type
     * that is in the accepted list of content types.
     *
     * @param multipartFile document to validate
     * @return boolean
     */
    public static boolean isDocumentOfAcceptedContentType(MultipartFile multipartFile) {
        final String contentType = multipartFile.getContentType();
        for(DocumentType documentType : DocumentType.values()) {
            if(contentType.equals(documentType.getContentType())) {
                return true;
            }
        }
        return false;
    }
}
