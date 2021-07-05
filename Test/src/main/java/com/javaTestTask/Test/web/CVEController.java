package com.javaTestTask.Test.web;

import com.javaTestTask.Test.CVE_Items;
import com.javaTestTask.Test.service.CVEService;
import com.javaTestTask.Test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Month;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CVEController {
    @Autowired
    private CVEService cveService;

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public String testing (ModelMap modelMap) {
        List<CVE_Items> cveItemsList = fileService.readFile();
        Map<Integer, Map<String, Integer>> map = cveService.getSeverityByDate(cveItemsList);
        System.out.println(map);
        modelMap.put("map", map);
        return "welcome";
    }


}
