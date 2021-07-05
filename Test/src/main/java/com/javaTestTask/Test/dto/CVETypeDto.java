package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVETypeDto {

    @JsonProperty("data_type")
    private String dataType;

    public CVETypeDto() {
    }

    public CVETypeDto(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "CVETypeDto [CVEDataType=" + dataType + "]";
    }

}
