package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.CalcSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcSettingsRepository extends JpaRepository<CalcSettings, Long> {
}
