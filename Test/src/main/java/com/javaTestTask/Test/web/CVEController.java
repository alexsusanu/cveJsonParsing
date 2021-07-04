package com.javaTestTask.Test.web;

import com.javaTestTask.Test.CVE_Items;
import com.javaTestTask.Test.service.CVEService;
import com.javaTestTask.Test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
public class CVEController {
    @Autowired
    private CVEService cveService;

    @Autowired
    private FileService fileService;


    @GetMapping("")
    public Map<Month, Integer> testing(){
        List<CVE_Items> cveItemsList = fileService.readFile();
        return cveService.cvePerMonth(cveItemsList);
    }
}
