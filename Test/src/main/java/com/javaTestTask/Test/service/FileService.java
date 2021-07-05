package com.javaTestTask.Test.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaTestTask.Test.dto.BaseMetricV2;
import com.javaTestTask.Test.dto.CVE;
import com.javaTestTask.Test.dto.CVEItem;
import com.javaTestTask.Test.dto.Impact;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private File file = new File("CVE.json");


    public List<CVEItem> readFile(){
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        List<CVEItem> cveItemsList = new ArrayList<>();
        BaseMetricV2 baseMetricV2;
        try{
            CVE cveMapper = objectMapper.readValue(file, CVE.class);
            for(CVEItem c : cveMapper.getCVEItem()){
                if(c.getImpact() != null) {
                    baseMetricV2 = c.getImpact().getBaseMetricV2();
                    if (baseMetricV2 != null) {
                        cveItemsList.add(new CVEItem(c.getCveType(), c.getImpact(), c.getPublishedDate()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cveItemsList;
    }
}
