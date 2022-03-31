package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface PercentSalaryResultRepository extends JpaRepository<PercentSalaryResult, Long> {

    @Query("select sum(psr.sum) from PercentSalaryResult psr join psr.timeSheets t join t.calcSettings cs " +
            "where cs.calcDate=:calcDate")
    BigDecimal getAllSumForMonth(@Param("calcDate") LocalDate calcDate);


    @Query("select (count (psr)>0)from PercentSalaryResult psr where  psr.staffList.id = :staffListId " +
            "and psr.timeSheets.id=:timeSheetsId")
    boolean isExistsPercentSalaryResult(@Param("staffListId") long staffListId, @Param("timeSheetsId") long timeSheetsId);
}