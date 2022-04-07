package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.UsersDTO;
import com.calcaddpayschoolbackend.entity.Users;
import com.calcaddpayschoolbackend.service.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper implements EntityToDTOMapper<Users, UsersDTO> {

    ModelMapper modelMapper = new ModelMapper();

    private final BCryptPasswordEncoder passwordEncoder;

    private final PeopleService peopleService;

    public UserDTOMapper(BCryptPasswordEncoder passwordEncoder, PeopleService peopleService) {
        this.passwordEncoder = passwordEncoder;
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
        Users users = modelMapper.map(dto, Users.class);
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        return users;
    }
}
