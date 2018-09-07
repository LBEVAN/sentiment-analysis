package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lbevan.sentiment.service.domain.misc.DocumentType;

/**
 * An {@link AnalysisRequest} implementation for a document.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentAnalysisRequestDto implements AnalysisRequest {

    private String requestId;
    private String documentId;
    private DocumentType documentType;

    /**
     * Constructor.
     *
     * @param requestId the request id
     * @param documentId the document id in db
     * @param documentType the document type
     */
    @JsonCreator
    public DocumentAnalysisRequestDto(@JsonProperty("requestId") String requestId,
                                      @JsonProperty("documentId") String documentId,
                                      @JsonProperty("documentType") DocumentType documentType) {
        this.requestId = requestId;
        this.documentId = documentId;
        this.documentType = documentType;
    }

    /**
     * Retrieve the request id.
     *
     * @return String the request id
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Retrieve the document id.
     *
     * @return String the document id
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * Retrieve the document type.
     *
     * @return DocumentType the document type
     */
    public DocumentType getDocumentType() {
        return documentType;
    }
}
