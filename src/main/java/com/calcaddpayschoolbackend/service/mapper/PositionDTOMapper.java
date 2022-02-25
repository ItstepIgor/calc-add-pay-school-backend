package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.PositionDTO;
import com.calcaddpayschoolbackend.entity.Position;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PositionDTOMapper implements EntityToDTOMapper<Position, PositionDTO> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public PositionDTO toDTO(Position entity, Object... args) {
        return modelMapper.map(entity, PositionDTO.class);
    }

    @Override
    public Position toEntity(PositionDTO dto, Object... args) {
        return modelMapper.map(dto, Position.class);
    }
}
