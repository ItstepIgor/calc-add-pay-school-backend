package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.exception.EntityExistsOnThisDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.exception.NotTimeSheetDayException;
import com.calcaddpayschoolbackend.exception.PercentValueException;
import com.calcaddpayschoolbackend.pojo.ResultAllSumForMonthPojo;
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

    private final AddPayFundService addPayFundService;

    private final AddPayTypeService addPayTypeService;

    private final PercentSalaryResultService percentSalaryResultService;

    private final BasicNormsService basicNormsService;

    private final CalcSettingsService calcSettingsService;

    private final TimeSheetService timeSheetService;

    private final StaffListService staffListService;

    private final PeopleService peopleService;

    public void createResult(AddPayResult addPayResult) {
        if (addPayResult.getPercent() > addPayResult.getAddPay().getMaxPercent()) {
            throw new PercentValueException();
        } else if (addPayResultRepository.isExistsAddPayResults(addPayResult.getAddPay().getId(),
                addPayResult.getTimeSheets().getId(), addPayResult.getStaffList().getId())) {
            throw new EntityExistsOnThisDateException(String.format("На текущую дату дополнительная оплата с кодом %s " +
                            "для %s уже сохранен", addPayResult.getAddPay().getAddPayCode(),
                    peopleService.findFIOPeopleById(addPayResult.getStaffList().getPeople().getId())));
        } else {
            BigDecimal sum = calcSumAddPay(addPayResult);
            addPayResult.setSum(sum);
            addPayResultRepository.save(addPayResult);
        }
    }

    public void saveBalanceToAddPayResult(AddPayResult addPayResult) {
        if (addPayResult.getPercent() > addPayResult.getAddPay().getMaxPercent()) {
            throw new PercentValueException();
        } else if (addPayResultRepository.isExistsAddPayResults(addPayResult.getAddPay().getId(),
                addPayResult.getTimeSheets().getId(), addPayResult.getStaffList().getId())) {
            AddPayResult addPayResultFromDb = addPayResultRepository.getAddPayResultsByAddPayAndStaffList(
                    addPayResult.getAddPay().getId(), addPayResult.getTimeSheets().getId(),
                    addPayResult.getStaffList().getId());
            addPayResultFromDb.setSum(addPayResultFromDb.getSum().add(addPayResult.getSum()));
            addPayResultRepository.save(addPayResultFromDb);
        } else {
            BigDecimal sum = calcSumAddPay(addPayResult);
            addPayResult.setSum(sum);
            addPayResultRepository.save(addPayResult);
        }
    }


    public BigDecimal calcSumAddPay(AddPayResult addPayResult) {
        int workDay = calcSettingsService.getMaxDateCalcSettings().getWorkingDays();
        int actualDaysWorked = timeSheetService
                .getMaxTimeSheetForStaffList(addPayResult.getStaffList().getId()).getActualDaysWorked();
        if (actualDaysWorked == 0) {
            throw new NotTimeSheetDayException(String.format("В табеле для сотрудника %s заполнено 0 рабочих дней",
                    peopleService.findFIOPeopleById(staffListService
                            .findStaffListById(addPayResult.getStaffList().getId()).getPeople().getId())));
        }
        double coefficient = addPayResult.getPercent() / 100;
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

    public ResultAllSumForMonthPojo getAllAddPayResultSumForMonth() {
        ResultAllSumForMonthPojo resultAllSumForMonthPojo = new ResultAllSumForMonthPojo();
        LocalDate calcDate = calcSettingsService.getMaxDateCalcSettings().getCalcDate();
        List<AddPayFund> addPayCurrentFund = addPayFundService.getAllAddPayCurrentFund(calcDate);
        BigDecimal allSumForMonth = percentSalaryResultService.getAllSumForMonth();
        for (AddPayFund addPayFund : addPayCurrentFund) {
            if (addPayFund.getAddPayTypes().getId() == 1) {
                resultAllSumForMonthPojo.setBonusSum(addPayFund.getAddPayFunds().subtract(addPayResultRepository
                        .getAllSumForMonth(1, calcDate) == null ? BigDecimal.valueOf(0)
                        : addPayResultRepository.getAllSumForMonth(1, calcDate)).subtract(allSumForMonth));
                resultAllSumForMonthPojo.setBonusName(addPayTypeService.findAddPayTypeById(1).getAddPayTypeName());
            } else if (addPayFund.getAddPayTypes().getId() == 2) {
                resultAllSumForMonthPojo.setComplicationSum(addPayFund.getAddPayFunds().subtract(addPayResultRepository
                        .getAllSumForMonth(2, calcDate) == null ? BigDecimal.valueOf(0)
                        : addPayResultRepository.getAllSumForMonth(2, calcDate)));
                resultAllSumForMonthPojo.setComplicationName(addPayTypeService.findAddPayTypeById(2).getAddPayTypeName());
            } else if (addPayFund.getAddPayTypes().getId() == 3) {
                resultAllSumForMonthPojo.setMotivationSum(addPayFund.getAddPayFunds().subtract(addPayResultRepository.getAllSumForMonth(3, calcDate) == null ?
                        BigDecimal.valueOf(0) : addPayResultRepository.getAllSumForMonth(3, calcDate)));
                resultAllSumForMonthPojo.setMotivationName(addPayTypeService.findAddPayTypeById(3).getAddPayTypeName());
            }
        }
        return resultAllSumForMonthPojo;
    }

    public void deleteResult(AddPayResult addPayResult) {
        addPayResultRepository.delete(addPayResult);
    }

    public void deleteResultById(Long id) {
        addPayResultRepository.deleteById(id);
    }
}
