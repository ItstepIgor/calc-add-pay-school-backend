package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.UsersDTO;
import com.calcaddpayschoolbackend.pojo.RolePojo;
import com.calcaddpayschoolbackend.service.CustomUserDetailsService;
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

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/get")
    public List<UsersDTO> findAll() {
        return userDTOMapper.toDTOs(usersService.getAllUsers());
    }

    @GetMapping("/getbyid")
    public UsersDTO findTimeSheetById(@RequestParam long id) {
        return userDTOMapper.toDTO(usersService.findUserById(id));
    }

    @GetMapping("/getByEnumRole")
    public List<RolePojo> getByEnumRole() {
        return usersService.getByEnumRole();
    }

//    @GetMapping("/getuser")
//    public UserDetails getUser(@RequestParam String login) {
//        return customUserDetailsService.loadUserByUsername(login);
//    }

    @PostMapping("/create")
    public void createUsers(@RequestBody UsersDTO usersDTO) {
        usersService.createUsers(userDTOMapper.toEntity(usersDTO));
    }

//    {
//            "peopleId": 1,
//            "password": "12345678",
//            "role": "ADMIN"
//    }


    @PutMapping("/update")
    public void updateUsers(@RequestBody UsersDTO usersDTO) {
        usersService.updateUsers(userDTOMapper.toEntity(usersDTO));
    }

    @DeleteMapping("/delete")
    public void deleteUsersById(@RequestParam long id) {
        usersService.deleteUsersById(id);
    }
//    localhost:8080/api/users/delete?id=3
}
