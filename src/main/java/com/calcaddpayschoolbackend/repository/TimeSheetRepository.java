package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
//    List<TimeSheet> findAllBy
}
