package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.AddPayResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        double coefficient = addPayResultDTO.getPercent() / 100;
        BigDecimal sumForAllDays = basicNormsService.getMaxDateBasicNorms().getBasicNormValue()
                .multiply(BigDecimal.valueOf(coefficient));
        return sumForAllDays.divide(BigDecimal.valueOf(workDay), 2, RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(actualDaysWorked));
    }


    public void updateResult(AddPayResult addPayResult) {
        addPayResultRepository.save(addPayResult);
    }

    public List<AddPayResult> getAllResults() {
        return addPayResultRepository.findAll();
    }

    public AddPayResult findAddPayResultById(long id) {
        return addPayResultRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Доп надбавка с id %d не найден", id)));
    }

    public void deleteResult(AddPayResult addPayResult) {
        addPayResultRepository.delete(addPayResult);
    }

    public void deleteResultById(Long id) {
        addPayResultRepository.deleteById(id);
    }
}
