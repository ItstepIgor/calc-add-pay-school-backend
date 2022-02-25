package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.PeopleDTO;
import com.calcaddpayschoolbackend.entity.People;

public class PeopleDTOMapper implements EntityToDTOMapper<People, PeopleDTO> {
    @Override
    public PeopleDTO toDTO(People entity, Object... args) {
        return null;
    }

    @Override
    public People toEntity(PeopleDTO dto, Object... args) {
        return null;
    }
}
