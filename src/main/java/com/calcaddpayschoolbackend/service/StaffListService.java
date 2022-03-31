package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.PercentSalary;
import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.EntityExistsOnThisDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffListService {
    private final StaffListRepository staffListRepository;

    private final PercentSalaryService percentSalaryService;

    private final PercentSalaryResultService percentSalaryResultService;

    private final CalcSettingsService calcSettingsService;

    private final TimeSheetService timeSheetService;

    private final PeopleService peopleService;

    @Transactional
    public void createStaffList(StaffList staffList) {
        if (staffListRepository.isExistStaffList(staffList.getPeople().getId(), staffList.getPosition().getId())) {
            throw new EntityExistsOnThisDateException(String.format("Штатное расписание для %s должность %s " +
                            "уже сохранено", peopleService.findFIOPeopleById(staffList.getPeople().getId()),
                    staffList.getPosition().getPositionName()));
        } else {
            staffListRepository.save(staffList);
        }
    }

    public void updateStaffList(StaffList staffList) {
        staffListRepository.save(staffList);
    }

    public List<StaffList> getAllStaffLists() {
        return staffListRepository.findAllByOrderByIdAsc();
    }

    public List<StaffList> getStaffListsWhoWorked() {
        return staffListRepository.findAllByDisabledIsTrueOrderByIdAsc();
    }

    public List<StaffList> findAllWhoDidNotWork() {
        return staffListRepository.findAllByDisabledIsFalseOrderByIdAsc();
    }

    public void deleteStaffList(StaffList staffList) {
        staffListRepository.delete(staffList);
    }

    public void deleteStaffListById(Long id) {
        staffListRepository.deleteById(id);
    }

    public StaffList findStaffListById(long id) {
        return staffListRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Штатное с id %d не найден", id)));
    }


    @Transactional
    public void createAllTimeSheetsWhoWorked() {
        List<StaffList> staffLists = getStaffListsWhoWorked();
        for (StaffList staffList : staffLists) {
            TimeSheet timeSheet = TimeSheet.builder()
                    .people(staffList.getPeople())
                    .calcSettings(calcSettingsService.getMaxDateCalcSettings())
                    .actualDaysWorked(0)
                    .build();
            timeSheetService.createTimeSheet(timeSheet);
        }
    }

    @Transactional
    public void calcAndSavePercentSalaryResult() {
        List<StaffList> staffLists = getStaffListsWhoWorked();

        PercentSalary percentDate = percentSalaryService.getMaxDatePercentSalary();
        int workingDays = calcSettingsService.getMaxDateCalcSettings().getWorkingDays();

        for (StaffList staffList : staffLists) {

            TimeSheet maxTimeSheetForStaffList = timeSheetService.getMaxTimeSheetForStaffList(staffList.getId());

            if (percentSalaryResultService.isExistsPercentSalaryResult(staffList.getId(), maxTimeSheetForStaffList.getId())) {
                throw new EntityExistsOnThisDateException(String.format("Премия для %s уже посчитана", peopleService
                        .findFIOPeopleById(staffList.getPeople().getId())));
            } else {
                BigDecimal bigDecimal = BigDecimal.valueOf(maxTimeSheetForStaffList.getActualDaysWorked()).multiply(BigDecimal
                                .valueOf((((double) percentDate.getPercentSalaryAll() / 100)
                                        * staffList.getSalary().doubleValue()) / workingDays))
                        .setScale(2, RoundingMode.HALF_UP);

                PercentSalaryResult percentSalaryResult = PercentSalaryResult.builder()
                        .staffList(staffList)
                        .timeSheets(maxTimeSheetForStaffList)
                        .percentSalary(percentDate)
                        .percent(percentDate.getPercentSalaryAll())
                        .sum(bigDecimal)
                        .build();
                percentSalaryResultService.createPercentSalaryResult(percentSalaryResult);

                if (staffList.isYoungSpecial()) {
//                BigDecimal bigDecimalYoungSpecial = BigDecimal.valueOf(maxTimeSheetForStaffList.getActualDaysWorked())
//                        .multiply(BigDecimal.valueOf((((double) percentDate.getPercentSalaryForYoungSpecial() / 100)
//                                * staffList.getSalary().doubleValue()) / workingDays))
//                        .setScale(2, RoundingMode.HALF_UP);
//             Уточнить какой процент брать  Если просто процент от оклада то берем эту переменную
                    BigDecimal bigDecimalYoungSpecial = BigDecimal.valueOf(((double) percentDate.getPercentSalaryForYoungSpecial() / 100)
                                    * staffList.getSalary().doubleValue())
                            .setScale(2, RoundingMode.HALF_UP);
                    PercentSalaryResult percentSalaryResultYoungSpecial = PercentSalaryResult.builder()
                            .staffList(percentSalaryResult.getStaffList())
                            .timeSheets(percentSalaryResult.getTimeSheets())
                            .percentSalary(percentSalaryResult.getPercentSalary())
                            .percent(percentDate.getPercentSalaryForYoungSpecial())
                            .sum(bigDecimalYoungSpecial)
                            .build();
                    percentSalaryResultService.createPercentSalaryResult(percentSalaryResultYoungSpecial);
                }
            }
        }
    }
}
