package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.PercentSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PercentSalaryRepository extends JpaRepository<PercentSalary, Long> {
    PercentSalary findFirstByOrderByPercentStartDateDesc();
}
