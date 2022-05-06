package com.calcaddpayschoolbackend.service;


import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import com.calcaddpayschoolbackend.repository.PercentSalaryResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PercentSalaryResultService {

    private final PercentSalaryResultRepository percentSalaryResultRepository;

    private final CalcSettingsService calcSettingsService;

    private final PercentSalaryService percentSalaryService;

    public void createPercentSalaryResult(PercentSalaryResult percentSalaryResult) {
        percentSalaryResultRepository.save(percentSalaryResult);
    }

    public void updatePercentSalaryResult(PercentSalaryResult percentSalaryResult) {
        percentSalaryResultRepository.save(percentSalaryResult);
    }

    public List<PercentSalaryResult> getAllPercentSalaryResults() {
        return percentSalaryResultRepository.findAllSortingByPosition();
    }

    public BigDecimal getAllSumForMonth() {
        BigDecimal allSumForMonth = percentSalaryResultRepository
                .getAllSumForMonth(calcSettingsService.getMaxDateCalcSettings().getCalcDate());
        if (allSumForMonth == null) {
            allSumForMonth = BigDecimal.valueOf(0);
        }
        return allSumForMonth;
    }

    public BigDecimal getSumForMonthWithPercent(long id) {
        int percent = 0;
        if (id == 1) {
            percent = percentSalaryService.getMaxDatePercentSalary().getPercentSalaryAll();
        } else if (id == 3) {
            percent = percentSalaryService.getMaxDatePercentSalary().getPercentSalaryForYoungSpecial();
        }
        BigDecimal allSumForMonthWithPercent = percentSalaryResultRepository
                .getSumForMonthWithPercent(calcSettingsService.getMaxDateCalcSettings().getCalcDate(), percent);
        if (allSumForMonthWithPercent == null) {
            allSumForMonthWithPercent = BigDecimal.valueOf(0);
        }
        return allSumForMonthWithPercent;
    }


    public void deletePercentSalaryResult(PercentSalaryResult percentSalaryResult) {
        percentSalaryResultRepository.delete(percentSalaryResult);
    }

    public boolean isExistsPercentSalaryResult(long timeSheetsId) {
        return percentSalaryResultRepository.isExistsPercentSalaryResult(timeSheetsId);
    }

    public void deletePercentSalaryResultById(Long id) {
        percentSalaryResultRepository.deleteById(id);
    }
}
