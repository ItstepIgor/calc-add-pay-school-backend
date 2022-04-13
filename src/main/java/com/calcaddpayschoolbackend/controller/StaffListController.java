package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.StaffListDTO;
import com.calcaddpayschoolbackend.service.StaffListService;
import com.calcaddpayschoolbackend.service.mapper.StaffListDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr/stafflist")
@RequiredArgsConstructor
public class StaffListController {


    private final StaffListDTOMapper staffListDTOMapper;

    private final StaffListService staffListService;

    @GetMapping("/get")
    public List<StaffListDTO> findAll() {
        return staffListDTOMapper.toDTOs(staffListService.getAllStaffLists());
    }

    @GetMapping("/getwhoworked")
    public List<StaffListDTO> findAllWhoWorked() {
        return staffListDTOMapper.toDTOs(staffListService.getStaffListsWhoWorked());
    }

    @GetMapping("/getwhodidnotwork")
    public List<StaffListDTO> findAllWhoDidNotWork() {
        return staffListDTOMapper.toDTOs(staffListService.findAllWhoDidNotWork());
    }

    @GetMapping("/getbyid")
    public StaffListDTO findStaffListById(@RequestParam long id) {
        return staffListDTOMapper.toDTO(staffListService.findStaffListById(id));
    }

    @GetMapping("/calcpercentsalary")
    public void calcPercentSalary() {
        staffListService.calcAndSavePercentSalaryResult();
    }

    @GetMapping("/createalltimesheets")
    public void createAllTimeSheetsWhoWorked() {
        staffListService.createAllTimeSheetsWhoWorked();
    }

    @PostMapping("/create")
    public void createStaffList(@RequestBody StaffListDTO staffListDTO) {
        staffListService.createStaffList(staffListDTOMapper.toEntity(staffListDTO));
    }

    //        {
//        "peopleId": 2,
//            "positionId": 2,
//            "salary": 300,
//            "disabled": false
//    }
    @PutMapping("/update")
    public void updateStaffList(@RequestBody StaffListDTO staffListDTO) {
        staffListService.updateStaffList(staffListDTOMapper.toEntity(staffListDTO));
    }

    @DeleteMapping("/delete")
    public void deleteStaffListById(@RequestParam long id) {
        staffListService.deleteStaffListById(id);
    }
}
