package io.github.lbevan.sentiment.service.domain.misc;

public enum  RequestStatus {

    REQUESTED("Requested"),
    IN_PROGRESS("In Progress"),
    COMPLETE("Complete"),
    FAILED("Failed");

    private String description;

    RequestStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
