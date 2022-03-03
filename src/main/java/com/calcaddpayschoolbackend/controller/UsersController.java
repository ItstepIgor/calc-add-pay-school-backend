package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.UsersDTO;
import com.calcaddpayschoolbackend.service.UsersService;
import com.calcaddpayschoolbackend.service.mapper.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserDTOMapper userDTOMapper;

    private final UsersService usersService;

    @GetMapping("/get")
    public List<UsersDTO> findAll() {
        return userDTOMapper.toDTOs(usersService.getAllUsers());
    }

    @PostMapping("/create")
    public void createUsers(@RequestBody UsersDTO usersDTO) {
        usersService.createUsers(userDTOMapper.toEntity(usersDTO));
    }

//    {
//            "peopleId": 1,
//            "password": "12345678",
//            "role": "ADMIN"
//    }


    @PostMapping("/update")
    public void updateUsers(@RequestBody UsersDTO usersDTO) {
        usersService.updateUsers(userDTOMapper.toEntity(usersDTO));
    }

    @GetMapping("/delete")
    public void deleteUsersById(@RequestParam long id) {
        usersService.deleteUsersById(id);
    }
//    localhost:8080/api/users/delete?id=3
}
