package com.javaTestTask.Test.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaTestTask.Test.CVE;
import com.javaTestTask.Test.CVE_Items;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
public class CVEItemsService {
    File file = new File("CVE.json");
    @Test
    public void readFile(){
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            CVE cve = objectMapper.readValue(file, CVE.class);
            System.out.println(cve.getCVE_data_format());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
