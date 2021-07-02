package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVE_Items {
    @JsonProperty("cve")
    private Map cve;
    @JsonProperty("problemtype")
    private Map problemType;
    @JsonProperty("references")
    private Map references;

    public Map getCve() {
        return cve;
    }

    public void setCve(Map cve) {
        this.cve = cve;
    }

    public Map getProblemType() {
        return problemType;
    }

    public void setProblemType(Map problemType) {
        this.problemType = problemType;
    }

    public Map getReferences() {
        return references;
    }

    public void setReferences(Map references) {
        this.references = references;
    }


    @Override
    public String toString() {
        return "cve{" +
                "cve='" + cve + '\'' +
                ", problemType='" + problemType + '\'' +
                ", references='" + references + '\'' +
                '}';
    }

}
