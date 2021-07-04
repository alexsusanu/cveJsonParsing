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
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CVEService {
    FileService fileService = new FileService();
    List<CVE_Items> cveItemsList = fileService.readFile();

    private List<Integer> yearList(List<CVE_Items> cveItemsList) {
        List<Integer> integerArrayList = new ArrayList<>();
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
    private Map<Month, Integer> cvePerMonth(List<CVE_Items> cveItemsList) throws ParseException {
        Map<Month, Integer> perMonth = new HashMap<>();
        int noCVE = 0;
        for(CVE_Items c : cveItemsList){
            Month month = parseDate(c.getPublishedDate()).getMonth();
            String cve = c.getCveType().getData_type();
            for(Month m : Month.values()){
                if(m.equals(month) && cve.equalsIgnoreCase("CVE")){
                    noCVE += 1;
                }
            }
            perMonth.put(month, noCVE);
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
     * @throws ParseException
     */
    private Map<Integer, Map<Month, Integer>> cvePerMonthPerYear(List<CVE_Items> cveItemsList) throws ParseException {
        Map<Integer, Map<Month, Integer>> perMonthPerYear = new HashMap<>();
        List<Integer> years = yearList(cveItemsList);
        years.stream().forEach(y -> {
            try {
                perMonthPerYear.put(y,cvePerMonth(cveItemsList));
            } catch (ParseException e) {
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
    private LocalDate parseDate(String dateFromFile) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        date = dateFormat.parse(dateFromFile);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
}
