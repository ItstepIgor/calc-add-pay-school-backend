package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.pojo.AddPayResultSumPojo;
import com.calcaddpayschoolbackend.service.AddPayResultService;
import com.calcaddpayschoolbackend.service.mapper.AddPayResultDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addpayresult")
@RequiredArgsConstructor
public class AddPayResultController {

    private final AddPayResultDTOMapper addPayResultDTOMapper;

    private final AddPayResultService addPayResultService;

    @PostMapping("/create")
    public void createAddPayResult(@RequestBody @Valid AddPayResultDTO addPayResultDTO) {
        addPayResultService.createResult(addPayResultDTOMapper.toEntity(addPayResultDTO));
    }

    @PutMapping("/update")
    public void updateAddPayResult(@RequestBody @Valid AddPayResultDTO addPayResultDTO) {
        addPayResultService.updateResult(addPayResultDTOMapper.toEntity(addPayResultDTO));
    }

    @GetMapping("/get")
    public List<AddPayResultDTO> getAllAddPayResult() {
        return addPayResultDTOMapper.toDTOs(addPayResultService.getAllResults());
    }

    @GetMapping("/getformonth")
    public List<AddPayResultDTO> getAllAddPayResultForMonth() {
        return addPayResultDTOMapper.toDTOs(addPayResultService.getAllAddPayResultForMonth());
    }

    @GetMapping("/getallsum")
    public AddPayResultSumPojo getAllAddPayResultSumForMonth() {
        return addPayResultService.getAllAddPayResultSumForMonth();
    }

    @GetMapping("/getbyid")
    public AddPayResultDTO findAddPayResultById(@RequestParam long id) {
        return addPayResultDTOMapper.toDTO(addPayResultService.findAddPayResultById(id));
    }

    @GetMapping("/delete")
    public void deleteAddPayResultById(@RequestParam long id) {
        addPayResultService.deleteResultById(id);
    }

}
