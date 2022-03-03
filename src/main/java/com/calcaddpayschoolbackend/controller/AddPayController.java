package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayDTO;
import com.calcaddpayschoolbackend.service.AddPayService;
import com.calcaddpayschoolbackend.service.mapper.AddPayDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addpay")
@RequiredArgsConstructor
public class AddPayController {

    private final AddPayDTOMapper addPayDTOMapper;

    private final AddPayService addPayService;

    @PostMapping("/create")
    public void createBasicNorms(@RequestBody AddPayDTO addPayDTO) {
        addPayService.createAddPay(addPayDTOMapper.toEntity(addPayDTO));
    }

    @PostMapping("/update")
    public void updateBasicNorms(@RequestBody AddPayDTO addPayDTO) {
        addPayService.updateAddPay(addPayDTOMapper.toEntity(addPayDTO));
    }

    @GetMapping("/get")
    public List<AddPayDTO> getAllBasicNorms() {
        return addPayDTOMapper.toDTOs(addPayService.getAllAddPays());
    }

    @GetMapping("/delete")
    public void deleteBasicNormsById(@RequestParam long id) {
        addPayService.deleteAddPayById(id);
    }
}
