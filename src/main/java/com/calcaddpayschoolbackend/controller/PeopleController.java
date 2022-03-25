package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.dto.PeopleDTO;
import com.calcaddpayschoolbackend.service.PeopleService;
import com.calcaddpayschoolbackend.service.mapper.PeopleDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleDTOMapper peopleDTOMapper;

    private final PeopleService peopleService;

    @GetMapping("/get")
    public List<PeopleDTO> findAll() {
        return peopleDTOMapper.toDTOs(peopleService.getAllPeople());
    }

    @GetMapping("/getbyid")
    public PeopleDTO findById(@RequestParam Long id) {
        return peopleDTOMapper.toDTO(peopleService.findPeopleById(id));
    }
//    localhost:8080/api/people/get?id=1
    @PostMapping("/create")
    public void createPeople(@RequestBody @Valid PeopleDTO peopleDTO) {
        peopleService.createPeople(peopleDTOMapper.toEntity(peopleDTO));
    }
//    {
//            "surName": "Иванов",
//            "firstName": "Семен",
//            "patronymic": "Иванович",
//            "personnelNumber": "125",
//            "phoneNumber": "+3752911111",
//            "address": "пр-т Ленина",
//            "youngSpecial": true
//    }

    @PostMapping("/update")
    public void updatePeople(@RequestBody @Valid PeopleDTO peopleDTO) {
        peopleService.updatePeople(peopleDTOMapper.toEntity(peopleDTO));
    }

    @GetMapping("/delete")
    public void deletePeople(@RequestParam long id) {
        peopleService.deletePeopleById(id);
    }
//    localhost:8080/api/people/delete?id=2

}
