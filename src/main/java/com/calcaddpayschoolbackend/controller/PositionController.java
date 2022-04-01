package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.dto.PositionDTO;
import com.calcaddpayschoolbackend.service.PositionService;
import com.calcaddpayschoolbackend.service.mapper.PositionDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionDTOMapper positionDTOMapper;

    private final PositionService positionService;

    @GetMapping("/get")
    public List<PositionDTO> findAll() {
        return positionDTOMapper.toDTOs(positionService.getAllPositions());
    }

    @GetMapping("/getbyid")
    public PositionDTO findPositionById(@RequestParam long id) {
        return positionDTOMapper.toDTO(positionService.findPositionById(id));
    }

    @PostMapping("/create")
    public void createPosition(@RequestBody @Valid PositionDTO positionDTO) {
        positionService.createPosition(positionDTOMapper.toEntity(positionDTO));
    }

    @PutMapping("/update")
    public void updatePosition(@RequestBody @Valid PositionDTO positionDTO) {
        positionService.updatePosition(positionDTOMapper.toEntity(positionDTO));
    }

    @DeleteMapping("/delete")
    public void deletePosition(@RequestParam long id) {
        positionService.deletePositionById(id);
    }
}
