package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.AddPayFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayFundService {
    private final AddPayFundRepository addPayFundRepository;

    public void createAddPayFund(AddPayFund addPayFund) {
        addPayFundRepository.save(addPayFund);
    }

    public void updateAddPayFund(AddPayFund addPayFund) {
        addPayFundRepository.save(addPayFund);
    }

    public List<AddPayFund> getAllAddPayFunds() {
        return addPayFundRepository.findAll();
    }

    public void deleteAddPayFund(AddPayFund addPayFund) {
        addPayFundRepository.delete(addPayFund);
    }

    public void deleteAddPayFundById(Long id) {
        addPayFundRepository.deleteById(id);
    }

    public AddPayFund findAddPayFundById(long id) {
        return addPayFundRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Фонд с id %d не найден", id)));
    }
}
