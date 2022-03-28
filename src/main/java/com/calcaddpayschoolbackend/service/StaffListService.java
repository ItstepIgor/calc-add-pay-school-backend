package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.PercentSalary;
import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.EntityExistsOnThisDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.CalcSettingsRepository;
import com.calcaddpayschoolbackend.repository.PercentSalaryRepository;
import com.calcaddpayschoolbackend.repository.PercentSalaryResultRepository;
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

    private final PercentSalaryRepository percentSalaryRepository;

    private final PercentSalaryResultRepository percentSalaryResultRepository;

    private final CalcSettingsRepository calcSettingsRepository;

    private final TimeSheetService timeSheetService;

    private final PeopleService peopleService;

    @Transactional
    public void createStaffList(StaffList staffList) {
        if (staffListRepository.isExistStaffList(staffList.getPeople().getId(), staffList.getPosition().getId())) {
            throw new EntityExistsOnThisDateException(String.format("Штатное расписание для %s %s должность %s " +
                            "уже сохранено", peopleService.findPeopleById(staffList.getPeople().getId()).getSurName(),
                    peopleService.findPeopleById(staffList.getPeople().getId()).getFirstName(),
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


    public void calcAndSavePercentSalaryResult() {
        List<StaffList> staffLists = getStaffListsWhoWorked();

        PercentSalary percentDate = percentSalaryRepository.findFirstByOrderByPercentStartDateDesc();
        int workingDays = calcSettingsRepository.findFirstByOrderByCalcDateDesc().getWorkingDays();

        for (StaffList staffList : staffLists) {

            TimeSheet maxTimeSheetForStaffList = timeSheetService.getMaxTimeSheetForStaffList(staffList.getId());

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
            percentSalaryResultRepository.save(percentSalaryResult);

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
                percentSalaryResultRepository.save(percentSalaryResultYoungSpecial);
            }
        }
    }
}
