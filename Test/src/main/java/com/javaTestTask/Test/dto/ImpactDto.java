package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpactDto {
    @JsonProperty("baseMetricV2")
    private BaseMetricV2Dto baseMetricV2;

    public BaseMetricV2Dto getBaseMetricV2() {
        return baseMetricV2;
    }

    public void setBaseMetricV2(BaseMetricV2Dto baseMetricV2) {
        this.baseMetricV2 = baseMetricV2;
    }

    @Override
    public String toString() {
        return "ImpactDto [baseMetricV2=" + baseMetricV2 + "]";
    }

}
