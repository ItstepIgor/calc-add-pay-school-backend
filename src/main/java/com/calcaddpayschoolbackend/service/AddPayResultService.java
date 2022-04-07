package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.exception.NotTimeSheetDayException;
import com.calcaddpayschoolbackend.exception.PercentValueException;
import com.calcaddpayschoolbackend.pojo.AddPayResultSumPojo;
import com.calcaddpayschoolbackend.repository.AddPayResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayResultService {
    private final AddPayResultRepository addPayResultRepository;

    private final BasicNormsService basicNormsService;

    private final CalcSettingsService calcSettingsService;

    private final TimeSheetService timeSheetService;

    private final StaffListService staffListService;

    private final PeopleService peopleService;

    public void createResult(AddPayResult addPayResult) {
        if (addPayResult.getPercent() > addPayResult.getAddPay().getMaxPercent()) {
            throw new PercentValueException();
        } else {
            addPayResultRepository.save(addPayResult);
        }
    }

    public BigDecimal calcSumAddPay(AddPayResultDTO addPayResultDTO) {
        int workDay = calcSettingsService.getMaxDateCalcSettings().getWorkingDays();
        int actualDaysWorked = timeSheetService
                .getMaxTimeSheetForStaffList(addPayResultDTO.getStaffListId()).getActualDaysWorked();
        if (actualDaysWorked == 0) {
            throw new NotTimeSheetDayException(String.format("В табеле для сотрудника %s заполнено 0 рабочих дней",
                    peopleService.findFIOPeopleById(staffListService
                            .findStaffListById(addPayResultDTO.getStaffListId()).getPeople().getId())));
        }
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
        return addPayResultRepository.findAllSortingByPosition();
    }

    public List<AddPayResult> getAllAddPayResultForMonth() {
        return addPayResultRepository
                .getAllAddPayResultForMonth(calcSettingsService.getMaxDateCalcSettings().getCalcDate());
    }

    public AddPayResult findAddPayResultById(long id) {
        return addPayResultRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Доп надбавка с id %d не найден", id)));
    }

    public AddPayResultSumPojo getAllAddPayResultSumForMonth() {
        AddPayResultSumPojo addPayResultSumPojo = new AddPayResultSumPojo();
        LocalDate calcDate = calcSettingsService.getMaxDateCalcSettings().getCalcDate();
        addPayResultSumPojo.setBonusSum(addPayResultRepository.getAllSumForMonth(1, calcDate) == null ?
                BigDecimal.valueOf(0) : addPayResultRepository.getAllSumForMonth(1, calcDate));
        addPayResultSumPojo.setComplicationSum(addPayResultRepository.getAllSumForMonth(2, calcDate) == null ?
                BigDecimal.valueOf(0) : addPayResultRepository.getAllSumForMonth(2, calcDate));
        addPayResultSumPojo.setMotivationSum(addPayResultRepository.getAllSumForMonth(3, calcDate) == null ?
                BigDecimal.valueOf(0) : addPayResultRepository.getAllSumForMonth(3, calcDate));
        return addPayResultSumPojo;
    }

    public void deleteResult(AddPayResult addPayResult) {
        addPayResultRepository.delete(addPayResult);
    }

    public void deleteResultById(Long id) {
        addPayResultRepository.deleteById(id);
    }
}
