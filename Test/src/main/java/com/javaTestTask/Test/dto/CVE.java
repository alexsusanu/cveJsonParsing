package com.javaTestTask.Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaTestTask.Test.dto.CVEItem;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVE {
    @JsonProperty("CVE_Items")
    private List<CVEItem> CVEItem;

    public CVE(){}

    public CVE(List<CVEItem> cveItems){
        this.CVEItem = cveItems;
    }

    public List<CVEItem> getCVEItem() {
        return CVEItem;
    }

    public void setCVEItem(List<CVEItem> CVEItem) {
        this.CVEItem = CVEItem;
    }

}
