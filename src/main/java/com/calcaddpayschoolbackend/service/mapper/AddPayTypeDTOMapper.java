package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.AddPayTypeDTO;
import com.calcaddpayschoolbackend.entity.AddPayType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddPayTypeDTOMapper implements EntityToDTOMapper<AddPayType, AddPayTypeDTO> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public AddPayTypeDTO toDTO(AddPayType entity, Object... args) {
        return modelMapper.map(entity, AddPayTypeDTO.class);
    }

    @Override
    public AddPayType toEntity(AddPayTypeDTO dto, Object... args) {
        return modelMapper.map(dto, AddPayType.class);
    }
}
