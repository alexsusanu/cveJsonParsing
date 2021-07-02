package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVEtype {
    @JsonProperty("data_type")
    private String data_type;
    @JsonProperty("data_format")
    private String data_format;
    @JsonProperty("data_version")
    private String data_version;
    @JsonProperty("CVE_data_meta")
    private CVE_data_meta CVE_data_meta;

    public com.javaTestTask.Test.CVE_data_meta getCVE_data_meta() {
        return CVE_data_meta;
    }

    public void setCVE_data_meta(com.javaTestTask.Test.CVE_data_meta CVE_data_meta) {
        this.CVE_data_meta = CVE_data_meta;
    }


    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getData_format() {
        return data_format;
    }

    public void setData_format(String data_format) {
        this.data_format = data_format;
    }

    public String getData_version() {
        return data_version;
    }

    public void setData_version(String data_version) {
        this.data_version = data_version;
    }

    @Override
    public String toString() {
        return "CVEtype{" +
                "data_type='" + data_type + '\'' +
                ", data_format='" + data_format + '\'' +
                ", data_version='" + data_version + '\'' +
                ", CVE_data_meta=" + CVE_data_meta +
                '}';
    }

}
