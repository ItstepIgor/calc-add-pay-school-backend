package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {


    @Query("select t from TimeSheet t join t.people p join p.staffLists s join t.calcSettings c " +
            "where  s.id = :staffListId and c.calcDate=:calcDate")
    TimeSheet getMaxTimeSheetForStaffList(@Param("staffListId") long staffListId, @Param("calcDate") LocalDate calcDate);

}
