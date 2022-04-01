package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.TimeSheetDTO;
import com.calcaddpayschoolbackend.pojo.TimeSheetUpdateDayPojo;
import com.calcaddpayschoolbackend.service.TimeSheetService;
import com.calcaddpayschoolbackend.service.mapper.TimeSheetDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/timesheet")
@RequiredArgsConstructor
public class TimeSheetController {

    private final TimeSheetDTOMapper timeSheetDTOMapper;

    private final TimeSheetService timeSheetService;

    @GetMapping("/get")
    public List<TimeSheetDTO> getAllTimeSheet() {
        return timeSheetDTOMapper.toDTOs(timeSheetService.getAllTimeSheets());
    }

    @GetMapping("/getbyid")
    public TimeSheetDTO findTimeSheetById(@RequestParam long id) {
        return timeSheetDTOMapper.toDTO(timeSheetService.findTimeSheetById(id));
    }

    @GetMapping("/getcurrenttimesheets")
    public List<TimeSheetDTO> getAllTimeSheetsWithMaxDate() {
        return timeSheetDTOMapper.toDTOs(timeSheetService.getAllTimeSheetsWithMaxDate());
    }

    @PostMapping("/create")
    public void createTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO) {
        timeSheetService.createTimeSheet(timeSheetDTOMapper.toEntity(timeSheetDTO));
    }

    @PutMapping("/update")
    public void updateTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO) {
        timeSheetService.updateTimeSheet(timeSheetDTOMapper.toEntity(timeSheetDTO));
    }

    @PostMapping("/updateday")
    public void updateTimeSheetDay(@RequestBody List<TimeSheetUpdateDayPojo> timeSheetUpdateDayPojos) {
        timeSheetService.updateTimeSheetDay(timeSheetUpdateDayPojos);
    }


    @DeleteMapping("/delete")
    public void deleteTimeSheetById(@RequestParam long id) {
        timeSheetService.deleteTimeSheetById(id);
    }
}
