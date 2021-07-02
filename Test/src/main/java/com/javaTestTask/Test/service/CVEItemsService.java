package com.javaTestTask.Test.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaTestTask.Test.CVE;
import com.javaTestTask.Test.CVE_Items;
import com.javaTestTask.Test.CVEtype;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
public class CVEItemsService {
    File file = new File("CVE.json");
    @Test
    public void readFile(){
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            CVE cveMapper = objectMapper.readValue(file, CVE.class);
            cveMapper.getCVE_Items().stream()
                                    .map(t -> t.getCveType().getCVE_data_meta().getId()).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
