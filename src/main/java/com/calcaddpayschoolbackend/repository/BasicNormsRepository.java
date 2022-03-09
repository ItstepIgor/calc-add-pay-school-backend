package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.BasicNorms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicNormsRepository extends JpaRepository<BasicNorms, Long> {
    BasicNorms findFirstByOrderByBasicNormDateDesc();
}
