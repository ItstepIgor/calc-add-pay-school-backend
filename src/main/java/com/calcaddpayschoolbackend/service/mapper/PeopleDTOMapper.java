package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.PeopleDTO;
import com.calcaddpayschoolbackend.entity.People;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PeopleDTOMapper implements EntityToDTOMapper<People, PeopleDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public PeopleDTO toDTO(People entity, Object... args) {
        return modelMapper.map(entity, PeopleDTO.class);
    }

    @Override
    public People toEntity(PeopleDTO dto, Object... args) {
        return modelMapper.map(dto, People.class);
    }
}
