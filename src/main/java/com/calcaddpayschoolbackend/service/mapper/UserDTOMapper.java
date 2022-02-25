package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.UserDTO;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper implements EntityToDTOMapper<User, UserDTO> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO toDTO(User entity, Object... args) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO dto, Object... args) {
        return modelMapper.map(dto, User.class);
    }
}
