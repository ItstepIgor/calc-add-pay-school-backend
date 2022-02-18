package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddPayRepository extends JpaRepository<AddPay, Long> {
}
