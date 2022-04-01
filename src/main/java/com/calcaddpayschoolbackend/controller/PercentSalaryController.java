package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.PercentSalaryDTO;
import com.calcaddpayschoolbackend.service.PercentSalaryService;
import com.calcaddpayschoolbackend.service.mapper.PercentSalaryDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/percentsalary")
@RequiredArgsConstructor
public class PercentSalaryController {

    private final PercentSalaryDTOMapper percentSalaryDTOMapper;

    private final PercentSalaryService percentSalaryService;

    @PostMapping("/create")
    public void createPercentSalary(@RequestBody @Valid PercentSalaryDTO percentSalaryDTO) {
        percentSalaryService.createPercentSalary(percentSalaryDTOMapper.toEntity(percentSalaryDTO));
    }

    @PutMapping("/update")
    public void updatePercentSalary(@RequestBody @Valid PercentSalaryDTO percentSalaryDTO) {
        percentSalaryService.updatePercentSalary(percentSalaryDTOMapper.toEntity(percentSalaryDTO));
    }

    @GetMapping("/get")
    public List<PercentSalaryDTO> getAllPercentSalary() {
        return percentSalaryDTOMapper.toDTOs(percentSalaryService.getAllPercentSalary());
    }

    @GetMapping("/getbyid")
    public PercentSalaryDTO findPercentSalaryById(@RequestParam long id) {
        return percentSalaryDTOMapper.toDTO(percentSalaryService.findPercentSalaryById(id));
    }

    @GetMapping("/getmaxdate")
    public PercentSalaryDTO getMaxDateBasicNorms() {
        return percentSalaryDTOMapper.toDTO(percentSalaryService.getMaxDatePercentSalary());
    }

    @DeleteMapping("/delete")
    public void deletePercentSalaryById(@RequestParam long id) {
        percentSalaryService.deletePercentSalaryById(id);
    }
}
