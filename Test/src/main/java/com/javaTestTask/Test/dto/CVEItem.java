package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVEItem {
    @JsonProperty("cve")
    private CVEType cveType;
    @JsonProperty("impact")
    private Impact impact;
    @JsonProperty("publishedDate")
    private String publishedDate;

    public CVEItem() {}

    public CVEItem(CVEType cveType, Impact impact, String publishedDate){
        this.cveType = cveType;
        this.impact = impact;
        this.publishedDate = publishedDate;
    }

    public Impact getImpact() {
        return impact;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public CVEType getCveType() {
        return cveType;
    }

    public void setCveType(CVEType cveType) {
        this.cveType = cveType;
    }

}
