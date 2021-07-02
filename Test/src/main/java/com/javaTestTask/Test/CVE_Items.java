package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVE_Items {
    @JsonProperty("cve")
    private CVEtype cveType;
//    @JsonProperty("problemtype")
//    private Map problemType;
//    @JsonProperty("references")
//    private Map references;

    public CVEtype getCveType() {
        return cveType;
    }

    public void setCveType(CVEtype cveType) {
        this.cveType = cveType;
    }

//    @Override
//    public String toString() {
//        return "cveType{" +
//                "cveType='" + cveType + '\'' +
//                ", problemType='" + problemType + '\'' +
//                ", references='" + references + '\'' +
//                '}';
//    }
//
}
