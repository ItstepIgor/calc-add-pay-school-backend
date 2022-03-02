package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddPayTypeRepository extends JpaRepository<AddPayType, Long> {
}
