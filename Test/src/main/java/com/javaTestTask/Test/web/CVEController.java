package com.javaTestTask.Test.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaTestTask.Test.dto.CVEItemDto;
import com.javaTestTask.Test.service.CVEService;
import com.javaTestTask.Test.service.FileService;

@Controller
public class CVEController {
    @Autowired
    private CVEService cveService;

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public String testing(ModelMap modelMap) {
        List<CVEItemDto> cveItemsList = fileService.readFile();
        Set<String> levels = cveService.getSeverity(cveItemsList);
        Map<Integer, Map<String, List<CVEItemDto>>> map = cveService.getTotalSeverityLevelsPerYear(cveItemsList);
        modelMap.put("map", map);
        modelMap.put("levels", levels);
        return "welcome";
    }

}
