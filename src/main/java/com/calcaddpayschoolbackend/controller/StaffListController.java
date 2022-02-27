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
}
