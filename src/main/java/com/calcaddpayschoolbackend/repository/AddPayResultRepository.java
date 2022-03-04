package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPayResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddPayResultRepository extends JpaRepository<AddPayResult, Long> {
}
