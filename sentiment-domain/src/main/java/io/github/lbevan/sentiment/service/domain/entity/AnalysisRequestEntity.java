package io.github.lbevan.sentiment.service.domain.entity;

import io.github.lbevan.sentiment.service.domain.misc.RequestStatus;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Map;

@Document(collection = "analysisRequest")
public class AnalysisRequestEntity extends BaseEntity {

    @Field("requestId")
    private String requestId;

    @Field("status")
    private RequestStatus status;

    @Field("dateRequested")
    private Instant dateRequested;

    @Field("attributes")
    private Map<String, Object> attributes;

    @PersistenceConstructor
    public AnalysisRequestEntity(String requestId,
                                 RequestStatus status,
                                 Instant dateRequested,
                                 Map<String, Object> attributes) {
        this.requestId = requestId;
        this.status = status;
        this.dateRequested = dateRequested;
        this.attributes = attributes;
    }

    public String getRequestId() {
        return requestId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Instant getDateRequested() {
        return dateRequested;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
