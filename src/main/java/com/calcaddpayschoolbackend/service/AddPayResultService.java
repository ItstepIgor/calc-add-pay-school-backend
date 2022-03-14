package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.repository.AddPayResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayResultService {
    private final AddPayResultRepository addPayResultRepository;

    private final BasicNormsService basicNormsService;

    private final CalcSettingsService calcSettingsService;

    private final TimeSheetService timeSheetService;

    public void createResult(AddPayResult addPayResult) {
        addPayResultRepository.save(addPayResult);
    }

    public BigDecimal calcSumAddPay(AddPayResultDTO addPayResultDTO) {
        int workDay = calcSettingsService.getMaxDateCalcSettings().getWorkingDays();
        int actualDaysWorked = timeSheetService
                .getMaxTimeSheetForStaffList(addPayResultDTO.getStaffListId()).getActualDaysWorked();
        BigDecimal sumTemp = BigDecimal.valueOf(addPayResultDTO.getPercent())
                .multiply(BigDecimal.valueOf(100)).divide(basicNormsService.getMaxDateBasicNorms().getBasicNormValue());
        return sumTemp.divide(BigDecimal.valueOf(workDay)).multiply(BigDecimal.valueOf(actualDaysWorked));
    }


    public void updateResult(AddPayResult addPayResult) {
        addPayResultRepository.save(addPayResult);
    }

    public List<AddPayResult> getAllResults() {
        return addPayResultRepository.findAll();
    }

    public void deleteResult(AddPayResult addPayResult) {
        addPayResultRepository.delete(addPayResult);
    }

    public void deleteResultById(Long id) {
        addPayResultRepository.deleteById(id);
    }
}
