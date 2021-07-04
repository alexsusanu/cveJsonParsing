package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class CVE_Items {
    @JsonProperty("cve")
    private CVEtype cveType;
    @JsonProperty("impact")
    private Impact impact;
    @JsonProperty("publishedDate")
    private String publishedDate;

    public CVE_Items() {}

    public CVE_Items(CVEtype cveType, Impact impact, String publishedDate){
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

    public CVEtype getCveType() {
        return cveType;
    }

    public void setCveType(CVEtype cveType) {
        this.cveType = cveType;
    }

}
