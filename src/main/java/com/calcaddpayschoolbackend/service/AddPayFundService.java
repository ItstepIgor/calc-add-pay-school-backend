package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import com.calcaddpayschoolbackend.exception.FundExistsOnThisDate;
import com.calcaddpayschoolbackend.exception.NoCurrentCalcDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.AddPayFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayFundService {
    private final AddPayFundRepository addPayFundRepository;

    public void createAddPayFund(AddPayFund addPayFund) {
        if (Period.between(addPayFund.getCalcSettings().getCalcDate(), LocalDate.now()).getDays() > 25) {
            throw new NoCurrentCalcDateException();
        } /*else if (addPayFund.getCalcSettings().getCalcDate().equals(addPayFundRepository.)) {
            throw new FundExistsOnThisDate("String");
        }*/ else {
            addPayFundRepository.save(addPayFund);
        }
    }

    public void updateAddPayFund(AddPayFund addPayFund) {
        addPayFundRepository.save(addPayFund);
    }

    public List<AddPayFund> getAllAddPayFunds() {
        return addPayFundRepository.findAll();
    }

    public List<AddPayFund> getAllAddPayCurrentFund(LocalDate date) {
        return addPayFundRepository.getAddPayCurrentFund(date);
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
