package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMetricV2 {
    @JsonProperty("severity")
    private String severity;

    public BaseMetricV2(){}

    public BaseMetricV2(String severity){
        this.severity = severity;
    }


    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
