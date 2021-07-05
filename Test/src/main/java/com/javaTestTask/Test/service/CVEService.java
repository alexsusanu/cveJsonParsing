package com.javaTestTask.Test.service;

import com.javaTestTask.Test.dto.BaseMetricV2;
import com.javaTestTask.Test.dto.CVEItem;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CVEService{

    /**
      * @param cveItemsList
      * @return list of all years, no duplicates, in order
     */
    public Set<Integer> yearList(List<CVEItem> cveItemsList) {
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
    public Map<Month, Integer> cvePerMonth(List<CVEItem> cveItemsList){
        Map<Month, Integer> perMonth = new HashMap<>();
        int noCVE = 0;
        for(CVEItem c : cveItemsList){
            String cve = c.getCveType().getDataType();
            Month month = null;
            try {
                month = parseDate(c.getPublishedDate()).getMonth();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(Month m : Month.values()){
                if(month.equals(m) && cve.equalsIgnoreCase("CVE")){
                    noCVE += 1;
                }
                perMonth.put(month, noCVE);
            }
        }
        //sort month
        perMonth = perMonth.entrySet().stream()
                           .sorted(Map.Entry.comparingByKey())
                           .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));
        return perMonth;
    }


    /**
     * @param cveItemsList
     * @return no of CVE per month for each year
     */
    public Map<Integer, Map<Month, Integer>> cvePerMonthPerYear(List<CVEItem> cveItemsList){
        Map<Integer, Map<Month, Integer>> perMonthPerYear = new HashMap<>();
        Set<Integer> years = yearList(cveItemsList);
        years.forEach(y -> {
            try {
                perMonthPerYear.put(y,cvePerMonth(cveItemsList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //sort year
        return perMonthPerYear.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));
    }


    /**
     * parse string into date
     * @param dateFromFile string date
     * @return LocalDate date
     * @throws ParseException
     */
    public LocalDate parseDate(String dateFromFile){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        try{
            date = dateFormat.parse(dateFromFile);
        }catch (ParseException e){
            e.printStackTrace();
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     *
     * @param cveItemsList
     * @return list of severity (low medium high)
     */
    public Set<String> getSeverity(List<CVEItem> cveItemsList){
        BaseMetricV2 baseMetricV2 = null;
        Set<String> severity = new TreeSet<>(Collections.reverseOrder());
        for(CVEItem c : cveItemsList){
            baseMetricV2 = c.getImpact().getBaseMetricV2();
            if(baseMetricV2 != null){
                severity.add(baseMetricV2.getSeverity());
            }
        }
        return severity;
    }

    public Map<Integer, Map<String, List<CVEItem>>> getTotalSeverityLevelsPerYear(List<CVEItem> cveItemsList){
        Set<String> severity = getSeverity(cveItemsList);
        Map<String, Integer> severityNo = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Map<String, List<CVEItem>>> data = cveItemsList.stream()
                .collect(Collectors.groupingBy((cveItem) -> parseDate(cveItem.getPublishedDate()).getYear(),
                            Collectors.groupingBy((cveItem) -> cveItem.getImpact().getBaseMetricV2().getSeverity())));
        return  data;
    }

}
