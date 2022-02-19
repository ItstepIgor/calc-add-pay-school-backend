package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPay;
import com.calcaddpayschoolbackend.repository.AddPayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayService {
    private final AddPayRepository addPayRepository;

    public void createAddPay(AddPay addPay) {
        addPayRepository.save(addPay);
    }

    public void updateAddPay(AddPay addPay) {
        addPayRepository.save(addPay);
    }

    public List<AddPay> getAllAddPays() {
        return addPayRepository.findAll();
    }

    public void deleteAddPay(AddPay addPay) {
        addPayRepository.delete(addPay);
    }

    public void deleteAddPayById(Long id) {
        addPayRepository.deleteById(id);
    }
}
