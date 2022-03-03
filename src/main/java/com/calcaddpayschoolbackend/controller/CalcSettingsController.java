package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.dto.CalcSettingsDTO;
import com.calcaddpayschoolbackend.service.CalcSettingsService;
import com.calcaddpayschoolbackend.service.mapper.CalcSettingsDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/calcsetting")
@RequiredArgsConstructor
public class CalcSettingsController {

    private final CalcSettingsService calcSettingsService;

    private final CalcSettingsDTOMapper calcSettingsDTOMapper;

    @GetMapping("/get")
    public List<CalcSettingsDTO> getCalcSetting() {
        return calcSettingsDTOMapper.toDTOs(calcSettingsService.getAllCalcSettings());
    }

    @GetMapping("/getmaxdate")
    public CalcSettingsDTO getMaxDateCalcSetting() {
        return calcSettingsDTOMapper.toDTO(calcSettingsService.getMaxDateCalcSettings());
    }

    @PostMapping("/create")
    public void createCalcSetting(@RequestBody CalcSettingsDTO calcSettingsDTO) {
        calcSettingsService.createCalcSettings(calcSettingsDTOMapper.toEntity(calcSettingsDTO));
    }

    @PostMapping("/update")
    public void updateCalcSetting(@RequestBody CalcSettingsDTO calcSettingsDTO) {
        calcSettingsService.createCalcSettings(calcSettingsDTOMapper.toEntity(calcSettingsDTO));
    }

    @GetMapping("/delete")
    public void deleteCalcSettings(@RequestParam long id) {
        calcSettingsService.deleteCalcSettingsId(id);
    }

}
