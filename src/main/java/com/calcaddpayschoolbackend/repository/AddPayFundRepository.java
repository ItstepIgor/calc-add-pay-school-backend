package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPayFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AddPayFundRepository extends JpaRepository<AddPayFund, Long> {

    @Query("select af from AddPayFund af join af.calcSettings c where c.calcDate=:date")
    List<AddPayFund> getAddPayCurrentFund(@Param("date") LocalDate date);
}
