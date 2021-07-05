package com.javaTestTask.Test.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVEDto {
    @JsonProperty("CVE_Items")
    private List<CVEItemDto> CVEItems;
    @JsonProperty("CVE_data_type")
    private String cveDataType;
    @JsonProperty("CVE_data_format")
    private String cveDataFormat;
    @JsonProperty("CVE_data_version")
    private String cveDataVersion;
    @JsonProperty("CVE_data_numberOfCVEs")
    private String cveDataNumberOfCVEs;
    @JsonProperty("CVE_data_timestamp")
    private String cveDataTimestamp;

    public CVEDto() {
    }

    public CVEDto(List<CVEItemDto> cveItems) {
        this.CVEItems = cveItems;
    }

    public List<CVEItemDto> getCVEItems() {
        return CVEItems;
    }

    public void setCVEItems(List<CVEItemDto> cVEItems) {
        CVEItems = cVEItems;
    }

    public String getCveDataType() {
        return cveDataType;
    }

    public void setCveDataType(String cveDataType) {
        this.cveDataType = cveDataType;
    }

    public String getCveDataFormat() {
        return cveDataFormat;
    }

    public void setCveDataFormat(String cveDataFormat) {
        this.cveDataFormat = cveDataFormat;
    }

    public String getCveDataVersion() {
        return cveDataVersion;
    }

    public void setCveDataVersion(String cveDataVersion) {
        this.cveDataVersion = cveDataVersion;
    }

    public String getCveDataNumberOfCVEs() {
        return cveDataNumberOfCVEs;
    }

    public void setCveDataNumberOfCVEs(String cveDataNumberOfCVEs) {
        this.cveDataNumberOfCVEs = cveDataNumberOfCVEs;
    }

    public String getCveDataTimestamp() {
        return cveDataTimestamp;
    }

    public void setCveDataTimestamp(String cveDataTimestamp) {
        this.cveDataTimestamp = cveDataTimestamp;
    }

}
