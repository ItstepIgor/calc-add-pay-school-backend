package com.calcaddpayschoolbackend.service;


import com.calcaddpayschoolbackend.entity.People;
import com.calcaddpayschoolbackend.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return peopleRepository.findAll();
    }

    public void deletePeople(People people) {
        peopleRepository.delete(people);
    }

    public void deletePeopleById(Long id) {
        peopleRepository.deleteById(id);
    }

    public Optional<People> findById(long id) {
        return peopleRepository.findById(id);
    }

}
