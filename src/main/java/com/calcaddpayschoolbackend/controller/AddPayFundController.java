package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayFundDTO;
import com.calcaddpayschoolbackend.service.AddPayFundService;
import com.calcaddpayschoolbackend.service.mapper.AddPayFundDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/addpayfund")
@RequiredArgsConstructor
public class AddPayFundController {

    private final AddPayFundDTOMapper addPayFundDTOMapper;

    private final AddPayFundService addPayFundService;

    @PostMapping("/create")
    public void createAddPayFund(@RequestBody @Valid AddPayFundDTO addPayFundDTO) {
        addPayFundService.createAddPayFund(addPayFundDTOMapper.toEntity(addPayFundDTO));
    }

    @PostMapping("/update")
    public void updateAddPayFund(@RequestBody @Valid AddPayFundDTO addPayFundDTO) {
        addPayFundService.updateAddPayFund(addPayFundDTOMapper.toEntity(addPayFundDTO));
    }

    @GetMapping("/get")
    public List<AddPayFundDTO> getAllAddPayFund() {
        return addPayFundDTOMapper.toDTOs(addPayFundService.getAllAddPayFunds());
    }

    @GetMapping("/getcurrentfund")
    public List<AddPayFundDTO> getAllAddPayCurrentFund(@RequestParam(value = "date")
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                               LocalDate date) {
        return addPayFundDTOMapper.toDTOs(addPayFundService.getAllAddPayCurrentFund(date));
    }

    @GetMapping("/getbyid")
    public AddPayFundDTO findAddPayFundById(@RequestParam long id) {
        return addPayFundDTOMapper.toDTO(addPayFundService.findAddPayFundById(id));
    }

    @GetMapping("/delete")
    public void deleteAddPayFundById(@RequestParam long id) {
        addPayFundService.deleteAddPayFundById(id);
    }
}
