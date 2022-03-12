package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.dto.AddPayResultDTOCreate;
import com.calcaddpayschoolbackend.service.AddPayResultService;
import com.calcaddpayschoolbackend.service.mapper.AddPayResultDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addpayresult")
@RequiredArgsConstructor
public class AddPayResultController {

    private final AddPayResultDTOMapper addPayResultDTOMapper;

    private final AddPayResultService addPayResultService;

    @PostMapping("/create")
    public void createAddPayResult(@RequestBody AddPayResultDTOCreate payResultDTOCreate) {
        addPayResultService.createResult(payResultDTOCreate);
    }

    @PostMapping("/update")
    public void updateAddPayResult(@RequestBody AddPayResultDTO addPayResultDTO) {
        addPayResultService.updateResult(addPayResultDTOMapper.toEntity(addPayResultDTO));
    }

    @GetMapping("/get")
    public List<AddPayResultDTO> getAllAddPayResult() {
        return addPayResultDTOMapper.toDTOs(addPayResultService.getAllResults());
    }

    @GetMapping("/delete")
    public void deleteAddPayResultById(@RequestParam long id) {
        addPayResultService.deleteResultById(id);
    }

}
