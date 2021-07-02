package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVE {
    @JsonProperty
    private String CVE_data_type;
    @JsonProperty
    private String CVE_data_format;
    @JsonProperty
    private String CVE_data_version;
    @JsonProperty
    private String CVE_data_numberOfCVEs;
    @JsonProperty
    private String CVE_data_timestamp;
    @JsonProperty
    private List<CVE_Items> CVE_Items;

    @Override
    public String toString() {
        return "CVE{" +
                "CVE_data_type='" + CVE_data_type + '\'' +
                ", CVE_data_format='" + CVE_data_format + '\'' +
                ", CVE_data_version='" + CVE_data_version + '\'' +
                ", CVE_data_numberOfCVEs='" + CVE_data_numberOfCVEs + '\'' +
                ", CVE_data_timestamp='" + CVE_data_timestamp + '\'' +
                ", CVE_Items=" + CVE_Items +
                '}';
    }


    public String getCVE_data_version() {
        return CVE_data_version;
    }

    public void setCVE_data_version(String CVE_data_version) {
        this.CVE_data_version = CVE_data_version;
    }

    public String getCVE_data_numberOfCVEs() {
        return CVE_data_numberOfCVEs;
    }

    public void setCVE_data_numberOfCVEs(String CVE_data_numberOfCVEs) {
        this.CVE_data_numberOfCVEs = CVE_data_numberOfCVEs;
    }

    public String getCVE_data_timestamp() {
        return CVE_data_timestamp;
    }

    public void setCVE_data_timestamp(String CVE_data_timestamp) {
        this.CVE_data_timestamp = CVE_data_timestamp;
    }

    public String getCVE_data_format() {
        return CVE_data_format;
    }

    public void setCVE_data_format(String CVE_data_format) {
        this.CVE_data_format = CVE_data_format;
    }

    public String getCVE_data_type() {
        return CVE_data_type;
    }

    public void setCVE_data_type(String CVE_data_type) {
        this.CVE_data_type = CVE_data_type;
    }

    public List<com.javaTestTask.Test.CVE_Items> getCVE_Items() {
        return CVE_Items;
    }

    public void setCVE_Items(List<com.javaTestTask.Test.CVE_Items> CVE_Items) {
        this.CVE_Items = CVE_Items;
    }

}
