package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.CalcSettingsDTO;
import com.calcaddpayschoolbackend.entity.CalcSettings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CalcSettingsDTOMapper implements EntityToDTOMapper<CalcSettings, CalcSettingsDTO> {

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public CalcSettingsDTO toDTO(CalcSettings entity, Object... args) {
        return modelMapper.map(entity, CalcSettingsDTO.class);
    }

    @Override
    public CalcSettings toEntity(CalcSettingsDTO dto, Object... args) {
        return modelMapper.map(dto, CalcSettings.class);
    }
}
