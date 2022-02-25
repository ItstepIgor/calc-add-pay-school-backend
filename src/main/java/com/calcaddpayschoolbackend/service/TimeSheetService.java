package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPay;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<TimeSheet> findAll(long id) {
        return timeSheetRepository.findById(id);
    }
}
