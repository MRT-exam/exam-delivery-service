package com.mtgo.exam.deliveryservice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeliveryStatus {

    @JsonProperty("ACTIVE")
    ACTIVE("ACTIVE"),
    @JsonProperty("COMPLETED")
    COMPLETED("COMPLETED");

    private String code;

    DeliveryStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
