package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
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
    public void createBasicNorms(@RequestBody AddPayResultDTO addPayResultDTO) {
        addPayResultService.createResult(addPayResultDTOMapper.toEntity(addPayResultDTO));
    }

    @PostMapping("/update")
    public void updateBasicNorms(@RequestBody AddPayResultDTO addPayResultDTO) {
        addPayResultService.updateResult(addPayResultDTOMapper.toEntity(addPayResultDTO));
    }

    @GetMapping("/get")
    public List<AddPayResultDTO> getAllBasicNorms() {
        return addPayResultDTOMapper.toDTOs(addPayResultService.getAllResults());
    }

    @GetMapping("/delete")
    public void deleteBasicNormsById(@RequestParam long id) {
        addPayResultService.deleteResultById(id);
    }

}
