package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.PercentSalary;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.PercentSalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PercentSalaryService {
    private final PercentSalaryRepository percentSalaryRepository;

    public void createPercentSalary(PercentSalary percentSalary) {
        percentSalaryRepository.save(percentSalary);
    }

    public void updatePercentSalary(PercentSalary percentSalary) {
        percentSalaryRepository.save(percentSalary);
    }

    public List<PercentSalary> getAllPercentSalary() {
        return percentSalaryRepository.findAll();
    }

    public PercentSalary getMaxDatePercentSalary() {
        return percentSalaryRepository.findFirstByOrderByPercentStartDateDesc();
    }

    public void deletePercentSalary(PercentSalary percentSalary) {
        percentSalaryRepository.delete(percentSalary);
    }

    public void deletePercentSalaryById(Long id) {
        percentSalaryRepository.deleteById(id);
    }

    public PercentSalary findPercentSalaryById(long id) {
        return percentSalaryRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Процент с id %d не найден", id)));
    }
}
