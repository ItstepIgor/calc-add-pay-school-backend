package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.pojo.BonusPojo;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final StaffListRepository staffListRepository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "E:\\";
        List<BonusPojo> bonusPojo = staffListRepository.findByAllBonus();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Bonus2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bonusPojo);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Bonus.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Bonus.pdf");
        }

        return "report generated in path : " + path;
    }
}
