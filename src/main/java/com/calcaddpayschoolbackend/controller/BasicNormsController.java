package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.BasicNormsDTO;
import com.calcaddpayschoolbackend.service.BasicNormsService;
import com.calcaddpayschoolbackend.service.mapper.BasicNormsDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basicnorms")
@RequiredArgsConstructor
public class BasicNormsController {

    private final BasicNormsDTOMapper basicNormsDTOMapper;

    private final BasicNormsService basicNormsService;

    @PostMapping("/create")
    public void createBasicNorms(@RequestBody BasicNormsDTO basicNormsDTO) {
        basicNormsService.createBasicNorms(basicNormsDTOMapper.toEntity(basicNormsDTO));
    }

    @PostMapping("/update")
    public void updateBasicNorms(@RequestBody BasicNormsDTO basicNormsDTO) {
        basicNormsService.updateBasicNorms(basicNormsDTOMapper.toEntity(basicNormsDTO));
    }

    @GetMapping("/get")
    public List<BasicNormsDTO> getAllBasicNorms() {
        return basicNormsDTOMapper.toDTOs(basicNormsService.getAllBasicNorms());
    }

    @GetMapping("/getbyid")
    public BasicNormsDTO findBasicNormsById(@RequestParam long id) {
        return basicNormsDTOMapper.toDTO(basicNormsService.findBasicNormsById(id));
    }

    @GetMapping("/getmaxdate")
    public BasicNormsDTO getMaxDateBasicNorms() {
        return basicNormsDTOMapper.toDTO(basicNormsService.getMaxDateBasicNorms());
    }

    @GetMapping("/delete")
    public void deleteBasicNormsById(@RequestParam long id) {
        basicNormsService.deleteBasicNormsById(id);
    }
}
