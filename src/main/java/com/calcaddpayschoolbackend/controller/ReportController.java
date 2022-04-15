package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("api/s/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/export/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }

    @RequestMapping(value = "/bonus", method = RequestMethod.GET)
    @ResponseBody
    public void getReportBonus(@RequestParam long id, HttpServletResponse responseReportBonus) throws JRException, IOException {
        JasperPrint jasperPrint = reportService.createReport(id);
        responseReportBonus.setContentType("application/x-pdf");
        responseReportBonus.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        final OutputStream outStream = responseReportBonus.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}
