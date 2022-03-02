package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
