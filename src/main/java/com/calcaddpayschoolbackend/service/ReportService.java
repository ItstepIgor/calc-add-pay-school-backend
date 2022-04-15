package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.pojo.BonusPojo;
import com.calcaddpayschoolbackend.pojo.ComplicationAndMotivationPojo;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final StaffListRepository staffListRepository;

    private final AddPayFundService addPayFundService;

    private final CalcSettingsService calcSettingsService;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "E:\\";
        List<BonusPojo> bonusPojo = staffListRepository.findByAllBonus();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ReportBonus.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bonusPojo);
        Map<String, Object> parameters = new HashMap<>();
        LocalDate localDate = calcSettingsService.getMaxDateCalcSettings().getCalcDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        parameters.put("CalcDate", date);
        parameters.put("NumberDateOrder", "Приказ будет на этом месте");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Bonus.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Bonus.pdf");
        }

        return "report generated in path : " + path;
    }

    public JasperPrint createReport(long addPayTypeId) throws FileNotFoundException, JRException {

        JRBeanCollectionDataSource dataSource = null;
        JasperReport jasperReport = null;

        if (addPayTypeId == 1) {
            System.out.println(addPayTypeId);
            List<BonusPojo> bonusPojo = staffListRepository.findByAllBonus();
            File file = ResourceUtils.getFile("classpath:ReportBonus.jrxml");
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            dataSource = new JRBeanCollectionDataSource(bonusPojo);

        } else if (addPayTypeId == 2 || addPayTypeId == 3) {
            System.out.println(addPayTypeId);
            List<ComplicationAndMotivationPojo> complicationAndMotivationPojo =
                    staffListRepository.findByAllComplicationAndMotivation(addPayTypeId);
            File file = ResourceUtils.getFile("classpath:ReportComplicationAndMotivation.jrxml");
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            dataSource = new JRBeanCollectionDataSource(complicationAndMotivationPojo);
        }
        Map<String, Object> parameters = new HashMap<>();
        LocalDate localDate = calcSettingsService.getMaxDateCalcSettings().getCalcDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        parameters.put("NumberDateOrder", addPayFundService.getAddPayFundNumberOrder(addPayTypeId));
        parameters.put("CalcDate", date);
        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }
}
