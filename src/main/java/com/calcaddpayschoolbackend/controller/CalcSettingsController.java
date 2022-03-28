package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.dto.CalcSettingsDTO;
import com.calcaddpayschoolbackend.service.CalcSettingsService;
import com.calcaddpayschoolbackend.service.mapper.CalcSettingsDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/getbyid")
    public CalcSettingsDTO findCalcSettingsById(@RequestParam long id) {
        return calcSettingsDTOMapper.toDTO(calcSettingsService.findCalcSettingById(id));
    }

    @GetMapping("/getmaxdate")
    public CalcSettingsDTO getMaxDateCalcSetting() {
        return calcSettingsDTOMapper.toDTO(calcSettingsService.getMaxDateCalcSettings());
    }

    @PostMapping("/create")
    public void createCalcSetting(@RequestBody @Valid CalcSettingsDTO calcSettingsDTO) {
        calcSettingsService.createCalcSettings(calcSettingsDTOMapper.toEntity(calcSettingsDTO));
    }

    @PutMapping("/update")
    public void updateCalcSetting(@RequestBody @Valid CalcSettingsDTO calcSettingsDTO) {
        calcSettingsService.updateCalcSettings(calcSettingsDTOMapper.toEntity(calcSettingsDTO));
    }

    @GetMapping("/delete")
    public void deleteCalcSettings(@RequestParam long id) {
        calcSettingsService.deleteCalcSettingsId(id);
    }

}
