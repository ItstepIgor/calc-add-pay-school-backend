package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.EntityExistsOnThisDateException;
import com.calcaddpayschoolbackend.exception.NoCurrentCalcDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.pojo.TimeSheetUpdateDayPojo;
import com.calcaddpayschoolbackend.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {
    private final TimeSheetRepository timeSheetRepository;

    private final CalcSettingsService calcSettingsService;

    private final PeopleService peopleService;

    public void createTimeSheet(TimeSheet timeSheet) {
        if (Period.between(timeSheet.getCalcSettings().getCalcDate(), LocalDate.now()).getDays() > 25) {
            throw new NoCurrentCalcDateException();
        } else if (timeSheet.getCalcSettings().getCalcDate().equals(timeSheetRepository
                .getMaxTimeSheetForPeople(timeSheet.getPeople().getId()))) {
            throw new EntityExistsOnThisDateException(String.format("На текущую дату табель для %s %s " +
                            "уже сохранен", peopleService.findPeopleById(timeSheet.getPeople().getId()).getSurName(),
                    peopleService.findPeopleById(timeSheet.getPeople().getId()).getFirstName()));
        } else {
            timeSheetRepository.save(timeSheet);
        }
    }

    public void updateTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.save(timeSheet);
    }

    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
        return timeSheetRepository.findTimeSheetByCalcSettingsEquals(calcSettingsService.getMaxDateCalcSettings());
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
            throw new EntityExistsOnThisDateException(String.format("Табель для %s отсутствует", timeSheetRepository
                    .getPeopleFioByStaffList(staffListId)));
        } else {
            timeSheet = timeSheetRepository.getMaxTimeSheetForStaffList(staffListId,
                    calcSettingsService.getMaxDateCalcSettings().getCalcDate());
        }
        return timeSheet;
    }
}
