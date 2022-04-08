package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.PercentSalaryResultDTO;
import com.calcaddpayschoolbackend.service.PercentSalaryResultService;
import com.calcaddpayschoolbackend.service.mapper.PercentSalaryResultDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/percentsalaryresult")
@RequiredArgsConstructor
public class PercentSalaryResultController {


    private final PercentSalaryResultDTOMapper percentSalaryResultDTOMapper;

    private final PercentSalaryResultService percentSalaryResultService;

    @PostMapping("/create")
    public void createPercentSalaryResult(@RequestBody PercentSalaryResultDTO percentSalaryResultDTO) {
        percentSalaryResultService.createPercentSalaryResult(percentSalaryResultDTOMapper.toEntity(percentSalaryResultDTO));
    }

    @PutMapping("/update")
    public void updatePercentSalaryResult(@RequestBody PercentSalaryResultDTO percentSalaryResultDTO) {
        percentSalaryResultService.updatePercentSalaryResult(percentSalaryResultDTOMapper.toEntity(percentSalaryResultDTO));
    }

    @GetMapping("/get")
    public List<PercentSalaryResultDTO> getAllPercentSalaryResult() {
        return percentSalaryResultDTOMapper.toDTOs(percentSalaryResultService.getAllPercentSalaryResults());
    }

//    @GetMapping("/getallsum")
//    public BigDecimal getAllSumForMonth() {
//        return percentSalaryResultService.getAllSumForMonth();
//    }

    @DeleteMapping("/delete")
    public void deletePercentSalaryResultById(@RequestParam long id) {
        percentSalaryResultService.deletePercentSalaryResultById(id);
    }

}
