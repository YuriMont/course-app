package com.api.server.models;

public enum Status {
    APPROVED("aproved"),
    REJECTED("rejected"),
    PENDING("pending");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
