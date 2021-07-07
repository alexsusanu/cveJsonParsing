package com.javaTestTask.Test.service;

import com.javaTestTask.Test.dto.CVEItem;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CVEService{

    public LocalDate parseDate(String dateFromFile){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        try{
            date = dateFormat.parse(dateFromFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     * Map<Year, Map<Severity Level (low medium high), Total severity>
     * @param cveItemsList
     * @return
     */
    public Map<Integer, Map<String, Long>> getTotalSeverityLevelsPerYear(List<CVEItem> cveItemsList){
        Map<Integer, Map<String, Long>> data = cveItemsList.stream()
                .collect(Collectors.groupingBy((cveItem) -> parseDate(cveItem.getPublishedDate()).getYear(),
                        Collectors.groupingBy((cveItem) -> cveItem.getImpact().getBaseMetricV2().getSeverity(), Collectors.counting())));
        Map<Integer, Map<String, Long>> sortedMap = new TreeMap<Integer, Map<String, Long>>(data);
        return  sortedMap;
    }

    public Set<String> severityLevelsSet(List<CVEItem> cveItemList){
        Set<String> data = cveItemList.stream()
                .map(e -> e.getImpact().getBaseMetricV2().getSeverity()).collect(Collectors.toSet());
        return data;
    }

    /**
     * Total number of CVEs per month
     * regardless of severity level, get total number of CVEs per month for each year
     */
    public Map<Object, Map<Object, Long>> getTotalCVEPerMonth(List<CVEItem> cveItemList){
        Map<Object, Map<Object, Long>> severityPerMonthTotal = cveItemList.stream()
                .collect(Collectors.groupingBy((cveItem) -> parseDate(cveItem.getPublishedDate()).getYear(),
                        Collectors.groupingBy((cveItem) -> String.format("%02d",parseDate(cveItem.getPublishedDate()).getMonthValue()),
                                Collectors.mapping(cveItem -> cveItem.getCveType().equals("CVE"), Collectors.counting()))));
        return severityPerMonthTotal;
    }
}
