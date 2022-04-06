package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.UsersDTO;
import com.calcaddpayschoolbackend.entity.Users;
import com.calcaddpayschoolbackend.service.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper implements EntityToDTOMapper<Users, UsersDTO> {

    ModelMapper modelMapper = new ModelMapper();

    private final PeopleService peopleService;

    public UserDTOMapper(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public UsersDTO toDTO(Users entity, Object... args) {
        UsersDTO usersDTO = modelMapper.map(entity, UsersDTO.class);
        if (entity.getPeople() != null) {
            usersDTO.setPeopleSurAndFirstName(peopleService.findFIOPeopleById(entity.getPeople().getId()));
        }
        return usersDTO;
    }

    @Override
    public Users toEntity(UsersDTO dto, Object... args) {
        return modelMapper.map(dto, Users.class);
    }
}
