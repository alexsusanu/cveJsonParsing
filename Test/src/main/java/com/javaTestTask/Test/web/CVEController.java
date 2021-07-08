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
    public String displayTable (ModelMap modelMap) {
        List<CVEItem> cveItemsList = fileService.readFile();
        Map<Integer, Map<String, Long>> severityPerYear = cveService.getTotalSeverityLevelsPerYear(cveItemsList);
        Set<String> severityLevelsSet = cveService.severityLevelsSet(cveItemsList); // severity levels list (low, med, high)
        modelMap.put("severityPerYear", severityPerYear);
        modelMap.put("severityLevels", severityLevelsSet);
        return "welcome";
    }
}
