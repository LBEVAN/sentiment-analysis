package io.github.lbevan.sentiment.service.domain.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class BaseAnalysisRequestEntity extends BaseEntity {

    @Field("requestId")
    protected String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
