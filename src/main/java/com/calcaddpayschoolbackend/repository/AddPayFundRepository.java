package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddPayFundRepository extends JpaRepository<AddPayFund, Long> {
}
