package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddPayRepository extends JpaRepository<AddPay, Long> {
}
