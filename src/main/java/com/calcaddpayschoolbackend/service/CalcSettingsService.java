package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.CalcSettings;
import com.calcaddpayschoolbackend.repository.CalcSettingsRepository;
import lombok.RequiredArgsConstructor;
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
        return calcSettingsRepository.findAll();
    }

    public void deleteCalcSettings(CalcSettings calcSettings) {
        calcSettingsRepository.delete(calcSettings);
    }

    public void deleteCalcSettingsId(Long id) {
        calcSettingsRepository.deleteById(id);
    }
}
