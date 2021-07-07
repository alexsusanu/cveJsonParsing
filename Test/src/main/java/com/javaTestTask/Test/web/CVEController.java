package com.javaTestTask.Test.web;

import com.javaTestTask.Test.dto.CVEItem;
import com.javaTestTask.Test.service.CVEService;
import com.javaTestTask.Test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CVEController {
    @Autowired
    private CVEService cveService;

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public String testing (ModelMap modelMap) {
        List<CVEItem> cveItemsList = fileService.readFile();
        Map<Integer, Map<String, Long>> severityPerYear = cveService.getTotalSeverityLevelsPerYear(cveItemsList);
        Set<String> severityLevelsSet = cveService.severityLevelsSet(cveItemsList); // severity levels list (low, med, high)

        severityPerYear.entrySet().stream().forEach(entry -> {
            entry.getValue().entrySet().stream().forEach(innerEntry -> {
                if(innerEntry.getKey().equals("HIGH")){
                    System.out.println(entry.getKey() + " " + innerEntry.getKey() + " " + innerEntry.getValue());
                }
            });
        });

        modelMap.put("severityLevels", severityLevelsSet);
        modelMap.put("severityPerYear", severityPerYear);
        return "welcome";
    }


}
