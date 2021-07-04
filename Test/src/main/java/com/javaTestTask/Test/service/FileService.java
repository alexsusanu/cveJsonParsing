package com.javaTestTask.Test.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaTestTask.Test.BaseMetricV2;
import com.javaTestTask.Test.CVE;
import com.javaTestTask.Test.CVE_Items;
import com.javaTestTask.Test.Impact;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FileService {
    private File file = new File("CVE.json");


    public List<CVE_Items> readFile(){
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        List<CVE_Items> cveItemsList = new ArrayList<>();
        BaseMetricV2 baseMetricV2 = null;
        Impact impact = null;
        try{
            CVE cveMapper = objectMapper.readValue(file, CVE.class);
            for(CVE_Items c : cveMapper.getCVE_Items()){
                if(c.getImpact() != null) {
                    baseMetricV2 = c.getImpact().getBaseMetricV2();
                    if (baseMetricV2 != null) {
                        cveItemsList.add(new CVE_Items(c.getCveType(), c.getImpact(), c.getPublishedDate()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cveItemsList;
    }
}
