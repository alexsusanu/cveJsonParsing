package com.javaTestTask.Test.web;

import com.javaTestTask.Test.dto.CVEItem;
import com.javaTestTask.Test.service.CVEService;
import com.javaTestTask.Test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class CVEController {
    @Autowired
    private CVEService cveService;

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public String testing (ModelMap modelMap) {
        List<CVEItem> cveItemsList = fileService.readFile();
        Set<String> levelsAmount = cveService.getSeverity(cveItemsList);
        Map<Integer, Map<String, List<CVEItem>>> map = cveService.getTotalSeverityLevelsPerYear(cveItemsList);
//        for(Map.Entry<Integer, Map<String, Integer>> entry : map.entrySet()){
//            for(Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()){
//                System.out.println(innerEntry.getValue());
//            }
//        }
        modelMap.put("entry", map);
        modelMap.put("levels", levelsAmount);
        return "welcome";
    }


}
