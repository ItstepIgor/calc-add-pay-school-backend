package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.StaffList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffListRepository extends JpaRepository<StaffList, Long> {
}
