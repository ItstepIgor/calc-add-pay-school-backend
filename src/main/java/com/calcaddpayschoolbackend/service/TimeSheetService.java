package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.EntityExistsOnThisDateException;
import com.calcaddpayschoolbackend.exception.NoCurrentCalcDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.pojo.TimeSheetUpdateDayPojo;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import com.calcaddpayschoolbackend.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {
    private final TimeSheetRepository timeSheetRepository;

    private final StaffListRepository staffListRepository;

    private final CalcSettingsService calcSettingsService;

    private final PeopleService peopleService;

    public void createTimeSheet(TimeSheet timeSheet) {
        if (Period.between(timeSheet.getCalcSettings().getCalcDate(), LocalDate.now()).getDays() > 25) {
            throw new NoCurrentCalcDateException();
        } else if ((timeSheet.getCalcSettings().getId() == calcSettingsService.getMaxDateCalcSettings().getId())
                && timeSheetRepository.isExistsTimeSheet(timeSheet.getStaffList().getId())) {
            throw new EntityExistsOnThisDateException(String.format("На текущую дату табель для %s " +
                    "уже сохранен", peopleService.findFIOPeopleById(timeSheet.getStaffList().getPeople().getId())));
        } else {
            timeSheetRepository.save(timeSheet);
        }
    }


    @org.springframework.transaction.annotation.Transactional
    public void createAllTimeSheetsWhoWorked() {
        List<StaffList> staffLists = staffListRepository.findAllByWhoWorked();
        for (StaffList staffList : staffLists) {
            TimeSheet timeSheet = TimeSheet.builder()
                    .staffList(staffList)
                    .calcSettings(calcSettingsService.getMaxDateCalcSettings())
                    .actualDaysWorked(0)
                    .build();
            createTimeSheet(timeSheet);
        }
    }


    public void updateTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.save(timeSheet);
    }

    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetRepository.findAllSortingByPosition();
    }


    public void deleteTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.delete(timeSheet);
    }

    public void deleteTimeSheetById(Long id) {
        timeSheetRepository.deleteById(id);
    }

    public TimeSheet findTimeSheetById(long id) {
        return timeSheetRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Табель с id %d не найден", id)));
    }

    public List<TimeSheet> getAllTimeSheetsWithMaxDate() {
        return timeSheetRepository
                .findTimeSheetByCalcDate(calcSettingsService.getMaxDateCalcSettings().getCalcDate());
    }

    @Transactional
    public void updateTimeSheetDay(List<TimeSheetUpdateDayPojo> timeSheetUpdateDayPojos) {
        for (TimeSheetUpdateDayPojo timeSheetUpdateDayPojo : timeSheetUpdateDayPojos) {
            timeSheetRepository.updateTimeSheetDay(timeSheetUpdateDayPojo.getId(),
                    timeSheetUpdateDayPojo.getActualDaysWorked());
        }
    }

    public TimeSheet getMaxTimeSheetForStaffList(long staffListId) {
        TimeSheet timeSheet;
        if (!timeSheetRepository.isExistsTimeSheetForStaffList(staffListId, calcSettingsService.getMaxDateCalcSettings()
                .getCalcDate())) {
            throw new EntityExistsOnThisDateException(String.format("Табель для %s отсутствует",
                    peopleService.findFIOPeopleById(staffListId)));
        } else {
            timeSheet = timeSheetRepository.getMaxTimeSheetForStaffList(staffListId,
                    calcSettingsService.getMaxDateCalcSettings().getCalcDate());
        }
        return timeSheet;
    }
}
