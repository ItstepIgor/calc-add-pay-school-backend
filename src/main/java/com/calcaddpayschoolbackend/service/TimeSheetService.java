package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {
    private final TimeSheetRepository timeSheetRepository;

    public void createTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.save(timeSheet);
    }

    public void updateTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.save(timeSheet);
    }

    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetRepository.findAll();
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
}
