package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPayType;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.AddPayTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayTypeService {
    private final AddPayTypeRepository addPayTypeRepository;

    public void createAddPayType(AddPayType addPayType) {
        addPayTypeRepository.save(addPayType);
    }

    public void updateAddPayType(AddPayType addPayType) {
        addPayTypeRepository.save(addPayType);
    }

    public List<AddPayType> getAllAddPayTypes() {
        return addPayTypeRepository.findAll();
    }

    public void deleteAddPayType(AddPayType addPayType) {
        addPayTypeRepository.delete(addPayType);
    }

    public void deleteAddPayTypeById(Long id) {
        addPayTypeRepository.deleteById(id);
    }

    public AddPayType findAddPayTypeById(long id) {
        return addPayTypeRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Тип начисления с id %d не найден", id)));
    }
}
