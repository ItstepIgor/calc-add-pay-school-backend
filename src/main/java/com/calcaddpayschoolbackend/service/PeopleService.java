package com.calcaddpayschoolbackend.service;


import com.calcaddpayschoolbackend.entity.People;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public void createPeople(People people) {
        peopleRepository.save(people);
    }

    public void updatePeople(People people) {
        peopleRepository.save(people);
    }

    public List<People> getAllPeople() {
        return peopleRepository.findAllByOrderByIdAsc();
    }

    public void deletePeople(People people) {
        peopleRepository.delete(people);
    }

    public void deletePeopleById(Long id) {
        peopleRepository.deleteById(id);
    }

    public People findPeopleById(long id) {
        return peopleRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Сотрудник с id %d не найден", id)));
    }

}
