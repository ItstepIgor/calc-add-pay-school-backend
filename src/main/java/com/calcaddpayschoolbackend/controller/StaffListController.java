package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.StaffListDTO;
import com.calcaddpayschoolbackend.service.StaffListService;
import com.calcaddpayschoolbackend.service.mapper.StaffListDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stafflist")
@RequiredArgsConstructor
public class StaffListController {


    private final StaffListDTOMapper staffListDTOMapper;

    private final StaffListService staffListService;

    @GetMapping("/get")
    public List<StaffListDTO> findAll() {
        return staffListDTOMapper.toDTOs(staffListService.getAllStaffLists());
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
    @PostMapping("/update")
    public void updateStaffList(@RequestBody StaffListDTO staffListDTO) {
        staffListService.createStaffList(staffListDTOMapper.toEntity(staffListDTO));
    }

    @GetMapping("/delete")
    public void deleteStaffListById(@RequestParam long id) {
        staffListService.deleteStaffListById(id);
    }
}
