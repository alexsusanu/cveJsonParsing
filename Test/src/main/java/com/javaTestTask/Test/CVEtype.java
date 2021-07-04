package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class CVEtype {
    @JsonProperty("data_type")
    private String data_type;

    public CVEtype(){}

    public CVEtype(String data_type){
        this.data_type = data_type;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

}
