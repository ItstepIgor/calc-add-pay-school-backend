package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.PercentSalaryDTO;
import com.calcaddpayschoolbackend.entity.PercentSalary;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PercentSalaryDTOMapper implements EntityToDTOMapper<PercentSalary, PercentSalaryDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public PercentSalaryDTO toDTO(PercentSalary entity, Object... args) {
        return modelMapper.map(entity, PercentSalaryDTO.class);
    }

    @Override
    public PercentSalary toEntity(PercentSalaryDTO dto, Object... args) {
        return modelMapper.map(dto, PercentSalary.class);
    }
}
