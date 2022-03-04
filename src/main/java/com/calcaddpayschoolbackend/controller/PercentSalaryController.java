package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.PercentSalaryDTO;
import com.calcaddpayschoolbackend.service.PercentSalaryService;
import com.calcaddpayschoolbackend.service.mapper.PercentSalaryDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/percentsalary")
@RequiredArgsConstructor
public class PercentSalaryController {

    private final PercentSalaryDTOMapper percentSalaryDTOMapper;

    private final PercentSalaryService percentSalaryService;

    @PostMapping("/create")
    public void createPercentSalary(@RequestBody PercentSalaryDTO percentSalaryDTO) {
        percentSalaryService.createPercentSalary(percentSalaryDTOMapper.toEntity(percentSalaryDTO));
    }

    @PostMapping("/update")
    public void updatePercentSalary(@RequestBody PercentSalaryDTO percentSalaryDTO) {
        percentSalaryService.updatePercentSalary(percentSalaryDTOMapper.toEntity(percentSalaryDTO));
    }

    @GetMapping("/get")
    public List<PercentSalaryDTO> getAllPercentSalary() {
        return percentSalaryDTOMapper.toDTOs(percentSalaryService.getAllPercentSalary());
    }

    @GetMapping("/delete")
    public void deletePercentSalaryById(@RequestParam long id) {
        percentSalaryService.deletePercentSalaryById(id);
    }
}
