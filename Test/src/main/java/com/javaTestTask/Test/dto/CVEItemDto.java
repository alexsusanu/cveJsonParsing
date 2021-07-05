package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVEItemDto {
    @JsonProperty("cve")
    private CVETypeDto cveType;
    @JsonProperty("impact")
    private ImpactDto impact;
    @JsonProperty("publishedDate")
    private String publishedDate;

    public CVEItemDto() {
    }

    public CVEItemDto(CVETypeDto cveType, ImpactDto impact, String publishedDate) {
        this.cveType = cveType;
        this.impact = impact;
        this.publishedDate = publishedDate;
    }

    public ImpactDto getImpact() {
        return impact;
    }

    public void setImpact(ImpactDto impact) {
        this.impact = impact;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public CVETypeDto getCveType() {
        return cveType;
    }

    public void setCveType(CVETypeDto cveType) {
        this.cveType = cveType;
    }

    @Override
    public String toString() {
        return "CVEItemDto [cveType=" + cveType + ", impact=" + impact + ", publishedDate=" + publishedDate + "]";
    }

}
