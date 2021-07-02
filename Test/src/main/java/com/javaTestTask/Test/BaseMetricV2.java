package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseMetricV2 {
    @JsonProperty("severity")
    private String severity;
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
