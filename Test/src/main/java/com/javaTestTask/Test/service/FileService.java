package com.javaTestTask.Test.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaTestTask.Test.dto.BaseMetricV2Dto;
import com.javaTestTask.Test.dto.CVEDto;
import com.javaTestTask.Test.dto.CVEItemDto;

@Service
public class FileService {
    private File file = new File("CVE.json");

    public List<CVEItemDto> readFile() {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        List<CVEItemDto> cveItemsList = new ArrayList<>();
        BaseMetricV2Dto baseMetricV2 = null;
        try {
            CVEDto cveMapper = objectMapper.readValue(file, CVEDto.class);
            for (CVEItemDto c : cveMapper.getCVEItems()) {
                if (c.getImpact() != null) {
                    baseMetricV2 = c.getImpact().getBaseMetricV2();
                    if (baseMetricV2 != null) {
                        cveItemsList.add(new CVEItemDto(c.getCveType(), c.getImpact(), c.getPublishedDate()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cveItemsList;
    }
}
