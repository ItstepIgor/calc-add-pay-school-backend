package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.UsersDTO;
import com.calcaddpayschoolbackend.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper implements EntityToDTOMapper<Users, UsersDTO> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UsersDTO toDTO(Users entity, Object... args) {
        return modelMapper.map(entity, UsersDTO.class);
    }

    @Override
    public Users toEntity(UsersDTO dto, Object... args) {
        return modelMapper.map(dto, Users.class);
    }
}
