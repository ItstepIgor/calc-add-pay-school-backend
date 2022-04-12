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
@RequestMapping("api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/export/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }


//    @GetMapping()
//    public void getDocument(HttpServletResponse response) throws IOException, JRException {
//
//        String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "SampleJasperTemplate.jasper").getAbsolutePath();
//// creating our list of beans
//        List<SampleBean> dataList = new ArrayList<SampleBean>();
//        SampleBean sampleBean = new SampleBean();
//        sampleBean.setName("some name");
//        sampleBean.setColor("red");
//        dataList.add(sampleBean);
//// creating datasource from bean list
//        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
//        Map parameters = new HashMap();
//        JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//        response.setContentType("application/pdf");
//        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
//    }

    @RequestMapping(value = "/bonus", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse bonus) throws JRException, IOException {
//        System.out.println("Проверка");
        JasperPrint jasperPrint = reportService.createReport();
        bonus.setContentType("application/x-pdf");
//        bonus.setHeader("Content-disposition", "inline; filename=bonus.pdf");
        bonus.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=bonus.pdf");
        final OutputStream outStream = bonus.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}
