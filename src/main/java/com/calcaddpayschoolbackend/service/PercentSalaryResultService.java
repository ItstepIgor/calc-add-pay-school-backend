package com.calcaddpayschoolbackend.service;


import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import com.calcaddpayschoolbackend.repository.PercentSalaryResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PercentSalaryResultService {

    private final PercentSalaryResultRepository percentSalaryResultRepository;

    public void createPercentSalaryResult(PercentSalaryResult percentSalaryResult) {
        percentSalaryResultRepository.save(percentSalaryResult);
    }

    public void updatePercentSalaryResult(PercentSalaryResult percentSalaryResult) {
        percentSalaryResultRepository.save(percentSalaryResult);
    }

    public List<PercentSalaryResult> getAllPercentSalaryResults() {
        return percentSalaryResultRepository.findAll();
    }

    public void deletePercentSalaryResult(PercentSalaryResult percentSalaryResult) {
        percentSalaryResultRepository.delete(percentSalaryResult);
    }

    public void deletePercentSalaryResultById(Long id) {
        percentSalaryResultRepository.deleteById(id);
    }
}
