package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.Users;
import com.calcaddpayschoolbackend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public void createUsers(Users users) {
        usersRepository.save(users);
    }

    public void updateUsers(Users users) {
        usersRepository.save(users);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void deleteUsers(Users users) {
        usersRepository.delete(users);
    }

    public void deleteUsersById(Long id) {
        usersRepository.deleteById(id);
    }
}
