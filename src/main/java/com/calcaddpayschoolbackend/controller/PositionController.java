package com.calcaddpayschoolbackend.controller;


import com.calcaddpayschoolbackend.dto.PositionDTO;
import com.calcaddpayschoolbackend.service.PositionService;
import com.calcaddpayschoolbackend.service.mapper.PositionDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionDTOMapper positionDTOMapper;

    private final PositionService  positionService;

    @GetMapping("/get")
    public List<PositionDTO> findAll() {
        return positionDTOMapper.toDTOs(positionService.getAllPositions());
    }

    @PostMapping("/create")
    public void createPosition(@RequestBody PositionDTO positionDTO) {
        positionService.createPosition(positionDTOMapper.toEntity(positionDTO));
    }

    @PostMapping("/update")
    public void updatePosition(@RequestBody PositionDTO positionDTO) {
        positionService.updatePosition(positionDTOMapper.toEntity(positionDTO));
    }

    @GetMapping("/delete")
    public void deletePosition(@RequestParam long id) {
        positionService.deletePositionById(id);
    }
}