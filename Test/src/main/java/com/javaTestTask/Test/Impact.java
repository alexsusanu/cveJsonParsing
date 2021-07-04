package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Impact {
    @JsonProperty("baseMetricV2")
    private BaseMetricV2 baseMetricV2;

    public Impact(){}

    public Impact(BaseMetricV2 baseMetricV2){
        this.baseMetricV2 = baseMetricV2;
    }

    public BaseMetricV2 getBaseMetricV2() {
        return baseMetricV2;
    }

    public void setBaseMetricV2(BaseMetricV2 baseMetricV2) {
        this.baseMetricV2 = baseMetricV2;
    }

}
