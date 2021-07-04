package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class CVE {
    @JsonProperty("CVE_Items")
    private List<CVE_Items> CVE_Items;

    public CVE(){}

    public CVE(List<CVE_Items> cveItems){
        this.CVE_Items = cveItems;
    }

    public List<CVE_Items> getCVE_Items() {
        return CVE_Items;
    }

    public void setCVE_Items(List<CVE_Items> CVE_Items) {
        this.CVE_Items = CVE_Items;
    }

}
