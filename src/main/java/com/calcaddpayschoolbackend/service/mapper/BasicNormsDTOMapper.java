package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.BasicNormsDTO;
import com.calcaddpayschoolbackend.entity.BasicNorms;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BasicNormsDTOMapper implements EntityToDTOMapper<BasicNorms, BasicNormsDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public BasicNormsDTO toDTO(BasicNorms entity, Object... args) {
        return modelMapper.map(entity, BasicNormsDTO.class);
    }

    @Override
    public BasicNorms toEntity(BasicNormsDTO dto, Object... args) {
        return modelMapper.map(dto, BasicNorms.class);
    }
}
