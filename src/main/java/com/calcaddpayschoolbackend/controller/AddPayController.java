package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayDTO;
import com.calcaddpayschoolbackend.service.AddPayService;
import com.calcaddpayschoolbackend.service.mapper.AddPayDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addpay")
@RequiredArgsConstructor
public class AddPayController {

    private final AddPayDTOMapper addPayDTOMapper;

    private final AddPayService addPayService;

    @PostMapping("/create")
    public void createBasicNorms(@RequestBody @Valid AddPayDTO addPayDTO) {
        addPayService.createAddPay(addPayDTOMapper.toEntity(addPayDTO));
    }

    @PutMapping("/update")
    public void updateBasicNorms(@RequestBody @Valid AddPayDTO addPayDTO) {
        addPayService.updateAddPay(addPayDTOMapper.toEntity(addPayDTO));
    }

    @GetMapping("/get")
    public List<AddPayDTO> getAllBasicNorms() {
        return addPayDTOMapper.toDTOs(addPayService.getAllAddPays());
    }

    @GetMapping("/getbyid")
    public AddPayDTO findAddPayFundById(@RequestParam long id) {
        return addPayDTOMapper.toDTO(addPayService.findAddPayById(id));
    }

    @GetMapping("/delete")
    public void deleteBasicNormsById(@RequestParam long id) {
        addPayService.deleteAddPayById(id);
    }
}
