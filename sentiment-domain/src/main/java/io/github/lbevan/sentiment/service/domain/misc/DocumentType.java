package io.github.lbevan.sentiment.service.domain.misc;

public enum DocumentType {

    MS_WORD("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    TXT("text/plain");

    private String contentType;

    /**
     * Constructor.
     *
     * @param contentType type of content
     */
    DocumentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Get the DocumentType based on the supplied content type.
     *
     * @param contentType type of content
     * @return DocumentType
     */
    public static DocumentType getFromContentType(String contentType) {
        for(DocumentType documentType : values()) {
            if(documentType.contentType.equals(contentType)) {
                return documentType;
            }
            continue;
        }
        return null;
    }
}
