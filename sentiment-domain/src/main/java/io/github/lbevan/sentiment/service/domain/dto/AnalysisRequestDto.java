package io.github.lbevan.sentiment.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lbevan.sentiment.service.domain.entity.AnalysisRequestEntity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;

public class AnalysisRequestDto {

    private String requestId;
    private String status;
    private String dateRequested;
    private Map<String, Object> attributes;

    /**
     * Constructor.
     * Convert the entity into the dto.
     *
     * @param entity the entity to convert
     */
    public AnalysisRequestDto(AnalysisRequestEntity entity) {
        this.requestId = entity.getRequestId();
        this.status = entity.getStatus().getDescription();
        this.dateRequested = formatInstant(entity.getDateRequested());
        this.attributes = entity.getAttributes();
    }

    /**
     * Constructor.
     * Used for serialisation.
     *
     * @param requestId the request id
     * @param status the status
     * @param dateRequested the date the analysis was requested
     * @param attributes the request attributes
     */
    @JsonCreator
    public AnalysisRequestDto(@JsonProperty("requestId") String requestId,
                              @JsonProperty("status") String status,
                              @JsonProperty("dateRequested") String dateRequested,
                              @JsonProperty("attributes") Map<String, Object> attributes) {
        this.requestId = requestId;
        this.status = status;
        this.dateRequested = dateRequested;
        this.attributes = attributes;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    private String formatInstant(final Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault());

        return formatter.format(instant);
    }
}
