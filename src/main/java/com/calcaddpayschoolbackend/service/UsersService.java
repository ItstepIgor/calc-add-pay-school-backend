package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.Role;
import com.calcaddpayschoolbackend.entity.Users;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.exception.NotDeleteException;
import com.calcaddpayschoolbackend.pojo.RolePojo;
import com.calcaddpayschoolbackend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<RolePojo> getByEnumRole() {
        return Arrays.stream(Role.values()).map(role -> new RolePojo(role.name())).collect(Collectors.toList());
    }

    public Users findUserById(long id) {
        return usersRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Пользователь с id %d не найден", id)));
    }

    public void deleteUsers(Users users) {
        usersRepository.delete(users);
    }

    public Users findByUserLogin(String login) {
        return usersRepository.findUsersByLogin(login);
    }

    public void deleteUsersById(Long id) {
        if (id == 1) {
            throw new NotDeleteException();
        } else {
            usersRepository.deleteById(id);
        }
    }

}
