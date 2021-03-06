package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import com.calcaddpayschoolbackend.exception.EntityExistsOnThisDateException;
import com.calcaddpayschoolbackend.exception.NoCurrentCalcDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.AddPayFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddPayFundService {
    private final AddPayFundRepository addPayFundRepository;

    private final CalcSettingsService calcSettingsService;
    private final AddPayTypeService addPayTypeService;

    @Transactional
    public void createAddPayFund(AddPayFund addPayFund) {
        if (Period.between(addPayFund.getCalcSettings().getCalcDate(), LocalDate.now()).getDays() > 25) {
            throw new NoCurrentCalcDateException();
        } else if (addPayFund.getCalcSettings().getCalcDate().equals(addPayFundRepository
                .getLastAddPayFund(addPayFund.getAddPayTypes().getId(),
                        calcSettingsService.getMaxDateCalcSettings().getId()).getCalcSettings().getCalcDate())) {
            throw new EntityExistsOnThisDateException(String.format("На текущую дату фонды для %s уже сохранены",
                    addPayTypeService.findAddPayTypeById(addPayFund.getAddPayTypes().getId()).getAddPayTypeName()));
        } else {
            addPayFundRepository.save(addPayFund);
        }
    }

    public void updateAddPayFund(AddPayFund addPayFund) {
        addPayFundRepository.save(addPayFund);
    }

    public List<AddPayFund> getAllAddPayFunds() {
        return addPayFundRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<AddPayFund> getAllAddPayCurrentFund(LocalDate date) {
        return addPayFundRepository.getAddPayCurrentFunds(date);
    }

    public String getAddPayFundNumberOrder(long addPayTypeId) {
        return addPayFundRepository.getLastAddPayFund(addPayTypeId,
                calcSettingsService.getMaxDateCalcSettings().getId()).getNumberOrder();
    }

    public String getAddPayFundNumberOrderTradeUnion(long addPayTypeId) {
        return addPayFundRepository.getLastAddPayFund(addPayTypeId,
                calcSettingsService.getMaxDateCalcSettings().getId()).getNumberOrderTradeUnion();
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
