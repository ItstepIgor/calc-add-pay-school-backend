package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.PercentSalary;
import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.CalcSettingsRepository;
import com.calcaddpayschoolbackend.repository.PercentSalaryRepository;
import com.calcaddpayschoolbackend.repository.PercentSalaryResultRepository;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void createStaffList(StaffList staffList) {
        staffListRepository.save(staffList);
    }

    public void updateStaffList(StaffList staffList) {
        staffListRepository.save(staffList);
    }

    public List<StaffList> getAllStaffLists() {
        return staffListRepository.findAllByOrderByIdAsc();
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

    public void calcPercentSalary() {
        List<StaffList> staffLists = getAllStaffLists();

        PercentSalary percentStartDateDesc = percentSalaryRepository.findFirstByOrderByPercentStartDateDesc();
        double percentSalaryAll = percentStartDateDesc.getPercentSalaryAll();
//        int percentSalaryForYoungSpecial = percentStartDateDesc.getPercentSalaryForYoungSpecial();
        int workingDays = calcSettingsRepository.findFirstByOrderByCalcDateDesc().getWorkingDays();

        for (StaffList staffList : staffLists) {
            PercentSalaryResult percentSalaryResult = null;
            System.out.println(timeSheetService.getMaxTimeSheetForStaffList(staffList.getId()));
            TimeSheet maxTimeSheetForStaffList = timeSheetService.getMaxTimeSheetForStaffList(staffList.getId());
            System.out.println(staffList);
            percentSalaryResult.setStaffList(staffList);
            System.out.println(percentStartDateDesc);
            percentSalaryResult.setPercentSalary(percentStartDateDesc);
            System.out.println(maxTimeSheetForStaffList);
            percentSalaryResult.setTimeSheets(maxTimeSheetForStaffList);
            percentSalaryResult.setPercent((int) percentSalaryAll);
            BigDecimal bigDecimal = BigDecimal.valueOf(maxTimeSheetForStaffList.getActualDaysWorked()).multiply(BigDecimal
                            .valueOf(((percentSalaryAll / 100) * staffList.getSalary().doubleValue()) / workingDays))
                    .setScale(2, RoundingMode.HALF_UP);
            percentSalaryResult.setSum(bigDecimal);
            System.out.println(percentSalaryResult);
            percentSalaryResultRepository.save(percentSalaryResult);
        }
    }
}
