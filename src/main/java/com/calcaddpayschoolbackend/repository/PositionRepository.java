package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
