package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.exception.*;
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
                addPayResult.getTimeSheets().getId())) {
            throw new EntityExistsOnThisDateException(String.format("На текущую дату дополнительная оплата с кодом %s " +
                            "для %s уже сохранен", addPayResult.getAddPay().getAddPayCode(),
                    peopleService.findFIOPeopleById(addPayResult.getTimeSheets().getStaffList().getPeople().getId())));
        } else {
            BigDecimal sum = calcSumAddPay(addPayResult);
            addPayResult.setSum(sum);
            addPayResultRepository.save(addPayResult);
        }
    }

    public void saveBalanceToAddPayResult(AddPayResult addPayResult) {
        Double checkSum = Double.valueOf(String.valueOf(calcBalanceSum(addPayResult.getAddPay().getAddPayTypes().getId())));
        if (checkSum > 10) {
            throw new BalanceSumException();
        } else {
            if (addPayResult.getPercent() > addPayResult.getAddPay().getMaxPercent()) {
                throw new PercentValueException();
            } else if (addPayResultRepository.isExistsAddPayResults(addPayResult.getAddPay().getId(),
                    addPayResult.getTimeSheets().getId())) {
                AddPayResult addPayResultFromDb = addPayResultRepository.getAddPayResultsByAddPayAndStaffList(
                        addPayResult.getAddPay().getId(), addPayResult.getTimeSheets().getId());
                addPayResultFromDb.setSum(addPayResultFromDb.getSum()
                        .add(calcBalanceSum(addPayResult.getAddPay().getAddPayTypes().getId())));
                addPayResultRepository.save(addPayResultFromDb);
            } else {
                BigDecimal sum = calcSumAddPay(addPayResult);
                addPayResult.setSum(sum);
                addPayResultRepository.save(addPayResult);
            }
        }


    }

    public BigDecimal calcSumAddPay(AddPayResult addPayResult) {
        int workDay = calcSettingsService.getMaxDateCalcSettings().getWorkingDays();
        int actualDaysWorked = timeSheetService
                .getMaxTimeSheetForStaffList(addPayResult.getTimeSheets().getStaffList().getId()).getActualDaysWorked();
        if (actualDaysWorked == 0) {
            throw new NotTimeSheetDayException(String.format("В табеле для сотрудника %s заполнено 0 рабочих дней",
                    peopleService.findFIOPeopleById(staffListService
                            .findStaffListById(addPayResult.getTimeSheets().getStaffList().getId()).getPeople().getId())));
        }
        double coefficient = addPayResult.getPercent() / 100;
        BigDecimal sumForAllDays = basicNormsService.getMaxDateBasicNorms().getBasicNormValue()
                .multiply(BigDecimal.valueOf(coefficient));
//        BigDecimal temp2 = sumForAllDays.multiply(BigDecimal.valueOf(actualDaysWorked)).divide(BigDecimal.valueOf(workDay));

        BigDecimal temp = sumForAllDays.multiply(BigDecimal.valueOf(actualDaysWorked));
        return temp.divide(BigDecimal.valueOf(workDay), 2, RoundingMode.CEILING);
    }

    public void updateResult(AddPayResult addPayResult) {
        BigDecimal sum = calcSumAddPay(addPayResult);
        addPayResult.setSum(sum);
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
        BigDecimal allSumForMonth = percentSalaryResultService.getAllSumForMonth();
        resultAllSumForMonthPojo.setBonusSum(calcBalanceSum(1).subtract(allSumForMonth));
        resultAllSumForMonthPojo.setBonusName(addPayTypeService.findAddPayTypeById(1).getAddPayTypeName());
        resultAllSumForMonthPojo.setComplicationSum(calcBalanceSum(2));
        resultAllSumForMonthPojo.setComplicationName(addPayTypeService.findAddPayTypeById(2).getAddPayTypeName());
        resultAllSumForMonthPojo.setMotivationSum(calcBalanceSum(3));
        resultAllSumForMonthPojo.setMotivationName(addPayTypeService.findAddPayTypeById(3).getAddPayTypeName());
        return resultAllSumForMonthPojo;
    }

    private BigDecimal calcBalanceSum(long id) {
        LocalDate calcDate = calcSettingsService.getMaxDateCalcSettings().getCalcDate();
        List<AddPayFund> addPayCurrentFund = addPayFundService.getAllAddPayCurrentFund(calcDate);
        BigDecimal sum = null;
        for (AddPayFund addPayFund : addPayCurrentFund) {
            if (addPayFund.getAddPayTypes().getId() == id) {
                sum = addPayFund.getAddPayFunds().subtract(addPayResultRepository.getAllSumForMonth(id, calcDate) == null
                        ? BigDecimal.valueOf(0) : addPayResultRepository.getAllSumForMonth(id, calcDate));
            }
        }
        return sum;
    }

    public void deleteResult(AddPayResult addPayResult) {
        addPayResultRepository.delete(addPayResult);
    }

    public void deleteResultById(Long id) {
        addPayResultRepository.deleteById(id);
    }
}
