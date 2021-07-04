package com.javaTestTask.Test.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.javaTestTask.Test.CVE;
import com.javaTestTask.Test.CVE_Items;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CVEService {
    File file = new File("CVE.json");
    @Test
    public void readFile() throws ParseException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        List<CVE_Items> cveItemsList = new ArrayList<>();
        try{
            CVE cveMapper = objectMapper.readValue(file, CVE.class);
            for(CVE_Items c : cveMapper.getCVE_Items()){
                cveItemsList.add(new CVE_Items(c.getCveType(), c.getImpact(), c.getPublishedDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(CVE_Items c : cveItemsList){
            System.out.println(parseDate(c.getPublishedDate()));
        }
    }

    private LocalDate parseDate(String dateFromFile) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        date = dateFormat.parse(dateFromFile);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
}
