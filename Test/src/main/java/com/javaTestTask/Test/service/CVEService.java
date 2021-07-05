package com.javaTestTask.Test.service;

import com.javaTestTask.Test.BaseMetricV2;
import com.javaTestTask.Test.CVE_Items;
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
    public Set<Integer> yearList(List<CVE_Items> cveItemsList) {
        Set<Integer> integerArrayList = new TreeSet<>();
        cveItemsList.forEach(y -> {
            try {
                integerArrayList.add(parseDate(y.getPublishedDate()).getYear());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return integerArrayList;
    }

    /**
     *
     * @param cveItemsList
     * @return total number of CVE per month for all years
     * @throws ParseException
     */
    public Map<Month, Integer> cvePerMonth(List<CVE_Items> cveItemsList){
        Map<Month, Integer> perMonth = new HashMap<>();
        int noCVE = 0;
        for(CVE_Items c : cveItemsList){
            String cve = c.getCveType().getData_type();
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
    public Map<Integer, Map<Month, Integer>> cvePerMonthPerYear(List<CVE_Items> cveItemsList){
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
    public LocalDate parseDate(String dateFromFile) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        date = dateFormat.parse(dateFromFile);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     *
     * @param cveItemsList
     * @return list of severity (low medium high)
     */
    public Set<String> getSeverity(List<CVE_Items> cveItemsList){
        BaseMetricV2 baseMetricV2 = null;
        Set<String> severity = new TreeSet<>(Collections.reverseOrder());
        for(CVE_Items c : cveItemsList){
            baseMetricV2 = c.getImpact().getBaseMetricV2();
            if(baseMetricV2 != null){
                severity.add(baseMetricV2.getSeverity());
            }
        }
        return severity;
    }

    public Map<Integer, Map<String, Integer>> getTotalSeverityLevelsPerYear(List<CVE_Items> cveItemsList){
        Set<String> severity = getSeverity(cveItemsList);
        Map<Integer, Map<String, Integer>> integerList = new TreeMap<>();
        Map<String, Integer> severityNo = new TreeMap<>(Collections.reverseOrder());
        int severityTotal = 0;
        for(CVE_Items c : cveItemsList){
            for(String s : severity){
                if(c.getImpact().getBaseMetricV2().getSeverity().equals(s)){
                    severityTotal += 1;
                    severityNo.put(s, severityTotal);
                }
            }
            try {
                integerList.put(parseDate(c.getPublishedDate()).getYear(), severityNo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return  integerList;
    }

}
