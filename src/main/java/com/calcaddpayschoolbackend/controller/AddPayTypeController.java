package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayTypeDTO;
import com.calcaddpayschoolbackend.service.AddPayTypeService;
import com.calcaddpayschoolbackend.service.mapper.AddPayTypeDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addpaytype")
@RequiredArgsConstructor
public class AddPayTypeController {

    private final AddPayTypeDTOMapper addPayTypeDTOMapper;

    private final AddPayTypeService addPayTypeService;

    @PostMapping("/create")
    public void createAddPayType(@RequestBody AddPayTypeDTO addPayTypeDTO) {
        addPayTypeService.createAddPayType(addPayTypeDTOMapper.toEntity(addPayTypeDTO));
    }

    @PostMapping("/update")
    public void updateAddPayType(@RequestBody AddPayTypeDTO addPayTypeDTO) {
        addPayTypeService.updateAddPayType(addPayTypeDTOMapper.toEntity(addPayTypeDTO));
    }

    @GetMapping("/get")
    public List<AddPayTypeDTO> getAllAddPayType() {
        return addPayTypeDTOMapper.toDTOs(addPayTypeService.getAllAddPayTypes());
    }
}
