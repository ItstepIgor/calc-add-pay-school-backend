package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.TimeSheetDTO;
import com.calcaddpayschoolbackend.service.TimeSheetService;
import com.calcaddpayschoolbackend.service.mapper.TimeSheetDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/timeSheet")
@RequiredArgsConstructor
public class TimeSheetController {

    private final TimeSheetDTOMapper timeSheetDTOMapper;

    private final TimeSheetService timeSheetService;

    @GetMapping("/get")
    public List<TimeSheetDTO> getAllTimeSheet() {
        return timeSheetDTOMapper.toDTOs(timeSheetService.getAllTimeSheets());
    }

    @PostMapping("/create")
    public void createTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO) {
        timeSheetService.createTimeSheet(timeSheetDTOMapper.toEntity(timeSheetDTO));
    }
}
