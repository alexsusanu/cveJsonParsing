package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMetricV2Dto {
    @JsonProperty("severity")
    private String severity;

    public BaseMetricV2Dto() {
    }

    public BaseMetricV2Dto(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "BaseMetricV2Dto [severity=" + severity + "]";
    }
}
