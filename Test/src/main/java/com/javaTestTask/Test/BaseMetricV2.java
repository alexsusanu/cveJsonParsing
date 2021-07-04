package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
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
