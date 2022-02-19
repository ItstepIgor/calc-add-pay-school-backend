package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPay;
import com.calcaddpayschoolbackend.entity.Result;
import com.calcaddpayschoolbackend.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;

    public void createResult(Result result) {
        resultRepository.save(result);
    }

    public void updateResult(Result result) {
        resultRepository.save(result);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public void deleteResult(Result result) {
        resultRepository.delete(result);
    }

    public void deleteResultById(Long id) {
        resultRepository.deleteById(id);
    }
}
