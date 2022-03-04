package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PercentSalaryResultRepository extends JpaRepository<PercentSalaryResult, Long> {

}
