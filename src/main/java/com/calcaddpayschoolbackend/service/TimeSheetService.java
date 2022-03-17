package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {
    private final TimeSheetRepository timeSheetRepository;

    private final CalcSettingsService calcSettingsService;

    public void createTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.save(timeSheet);
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

    public List<TimeSheet> getMaxDateTimeSheets() {
        return null;
    }

    public void updateTimeSheetDay(long id, int actualDaysWorked) {
        timeSheetRepository.updateTimeSheetDay(id, actualDaysWorked);
    }

    public TimeSheet getMaxTimeSheetForStaffList(long staffListId) {
        return timeSheetRepository.getMaxTimeSheetForStaffList(staffListId, calcSettingsService.getMaxDateCalcSettings().getCalcDate());
    }
}
