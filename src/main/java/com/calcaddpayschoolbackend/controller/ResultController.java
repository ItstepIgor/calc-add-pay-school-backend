package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.dto.ResultDTO;
import com.calcaddpayschoolbackend.service.ResultService;
import com.calcaddpayschoolbackend.service.mapper.ResultDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultDTOMapper resultDTOMapper;

    private final ResultService resultService;

    @PostMapping("/create")
    public void createBasicNorms(@RequestBody ResultDTO resultDTO) {
        resultService.createResult(resultDTOMapper.toEntity(resultDTO));
    }

    @PostMapping("/update")
    public void updateBasicNorms(@RequestBody ResultDTO resultDTO) {
        resultService.updateResult(resultDTOMapper.toEntity(resultDTO));
    }

    @GetMapping("/get")
    public List<ResultDTO> getAllBasicNorms() {
        return resultDTOMapper.toDTOs(resultService.getAllResults());
    }

    @GetMapping("/delete")
    public void deleteBasicNormsById(@RequestParam long id) {
        resultService.deleteResultById(id);
    }

}
