package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.BasicNorms;
import com.calcaddpayschoolbackend.repository.BasicNormsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicNormsService {
    private final BasicNormsRepository basicNormsRepository;

    public void createBasicNorms(BasicNorms basicNorms) {
        basicNormsRepository.save(basicNorms);
    }

    public void updateBasicNorms(BasicNorms basicNorms) {
        basicNormsRepository.save(basicNorms);
    }

    public List<BasicNorms> getAllBasicNorms() {
        return basicNormsRepository.findAll();
    }

    public void deleteBasicNorms(BasicNorms basicNorms) {
        basicNormsRepository.delete(basicNorms);
    }

    public void deleteBasicNormsById(Long id) {
        basicNormsRepository.deleteById(id);
    }
}
