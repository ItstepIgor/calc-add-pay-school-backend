package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.CalcSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalcSettingsRepository extends JpaRepository<CalcSettings, Long> {

    CalcSettings findFirstByOrderByCalcDateDesc();
}
