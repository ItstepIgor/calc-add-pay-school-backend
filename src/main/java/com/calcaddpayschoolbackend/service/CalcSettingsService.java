package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.CalcSettings;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.CalcSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalcSettingsService {
    private final CalcSettingsRepository calcSettingsRepository;

    public void createCalcSettings(CalcSettings calcSettings) {
        calcSettingsRepository.save(calcSettings);
    }

    public void updateCalcSettings(CalcSettings calcSettings) {
        calcSettingsRepository.save(calcSettings);
    }

    public List<CalcSettings> getAllCalcSettings() {
        return calcSettingsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public CalcSettings getMaxDateCalcSettings() {
        return calcSettingsRepository.findFirstByOrderByCalcDateDesc();
    }

    public void deleteCalcSettings(CalcSettings calcSettings) {
        calcSettingsRepository.delete(calcSettings);
    }

    public void deleteCalcSettingsId(Long id) {
        calcSettingsRepository.deleteById(id);
    }

    public CalcSettings findCalcSettingById(long id) {
        return calcSettingsRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Настройка расчета с id %d не найден", id)));
    }


}
