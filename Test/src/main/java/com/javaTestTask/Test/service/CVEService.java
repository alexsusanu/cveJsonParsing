package com.javaTestTask.Test.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaTestTask.Test.dto.BaseMetricV2Dto;
import com.javaTestTask.Test.dto.CVEItemDto;

@Service
public class CVEService {

    /**
     * @param cveItemsList
     * @return list of all years, no duplicates, in order
     */
    public Set<Integer> yearList(List<CVEItemDto> cveItemsList) {
        Set<Integer> integerArrayList = new TreeSet<>();
        cveItemsList.forEach(y -> {
            integerArrayList.add(parseDate(y.getPublishedDate()).getYear());
        });
        return integerArrayList;
    }

    /**
     *
     * @param cveItemsList
     * @return total number of CVE per month for all years
     * @throws ParseException
     */
    public Map<Month, Integer> cvePerMonth(List<CVEItemDto> cveItemsList) {
        Map<Month, Integer> perMonth = new HashMap<>();
        int noCVE = 0;
        for (CVEItemDto c : cveItemsList) {
            String cve = c.getCveType().getDataType();
            Month month = null;
            try {
                month = parseDate(c.getPublishedDate()).getMonth();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Month m : Month.values()) {
                if (month.equals(m) && cve.equalsIgnoreCase("CVE")) {
                    noCVE += 1;
                }
                perMonth.put(month, noCVE);
            }
        }
        // sort month
        perMonth = perMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));
        return perMonth;
    }

    /**
     * @param cveItemsList
     * @return no of CVE per month for each year
     */
    public Map<Integer, Map<Month, Integer>> cvePerMonthPerYear(List<CVEItemDto> cveItemsList) {
        Map<Integer, Map<Month, Integer>> perMonthPerYear = new HashMap<>();
        Set<Integer> years = yearList(cveItemsList);
        years.forEach(y -> {
            try {
                perMonthPerYear.put(y, cvePerMonth(cveItemsList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // sort year
        return perMonthPerYear.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));
    }

    /**
     * parse string into date
     * 
     * @param dateFromFile string date
     * @return LocalDate date
     * @throws ParseException
     */
    public LocalDate parseDate(String dateFromFile) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateFromFile);
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     *
     * @param cveItemsList
     * @return list of severity (low medium high)
     */
    public Set<String> getSeverity(List<CVEItemDto> cveItemsList) {
        BaseMetricV2Dto baseMetricV2 = null;
        Set<String> severity = new TreeSet<>(Collections.reverseOrder());
        for (CVEItemDto c : cveItemsList) {
            baseMetricV2 = c.getImpact().getBaseMetricV2();
            if (baseMetricV2 != null) {
                severity.add(baseMetricV2.getSeverity());
            }
        }
        return severity;
    }

    public Map<Integer, Map<String, List<CVEItemDto>>> getTotalSeverityLevelsPerYear(List<CVEItemDto> cveItemsList) {
        Set<String> severity = getSeverity(cveItemsList);
        Map<Integer, Map<String, Integer>> integerList = new TreeMap<>();
        Map<String, Integer> severityNo = new TreeMap<>(Collections.reverseOrder());

        // group by year first, then group by severity within each "year bucket"
        Map<Integer, Map<String, List<CVEItemDto>>> data = cveItemsList.stream()
                .collect(Collectors.groupingBy((cveItem) -> parseDate(cveItem.getPublishedDate()).getYear(),
                        Collectors.groupingBy((cveItem) -> cveItem.getImpact().getBaseMetricV2().getSeverity())));

        System.out.println(data);

//        for (CVEItemDto c : cveItemsList) {
//            for (String s : severity) {
//                if (c.getImpact().getBaseMetricV2().getSeverity().equals(s)) {
//                    // LOW, MEDIUM, HIGH
//                    if (severityNo.containsKey(s)) {
//                        severityNo.put(s, severityNo.get(s) + 1);
//                    } else {
//                        severityNo.put(s, 1);
//                    }
//                }
//            }
//            integerList.put(parseDate(c.getPublishedDate()).getYear(), severityNo);
//        }
        return data;
    }

}
