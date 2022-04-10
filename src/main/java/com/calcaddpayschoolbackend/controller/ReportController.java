package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }
}
