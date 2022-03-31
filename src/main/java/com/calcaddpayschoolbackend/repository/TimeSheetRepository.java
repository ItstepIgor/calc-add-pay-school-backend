package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.CalcSettings;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {


    @Query("select t from TimeSheet t join t.people p join p.staffLists s join t.calcSettings c " +
            "where  s.id = :staffListId and c.calcDate=:calcDate")
    TimeSheet getMaxTimeSheetForStaffList(@Param("staffListId") long staffListId, @Param("calcDate") LocalDate calcDate);


    @Query("select (count (t)>0)from TimeSheet t join t.people p join p.staffLists s join t.calcSettings c " +
            "where  s.id = :staffListId and c.calcDate=:calcDate")
    boolean isExistsTimeSheetForStaffList(@Param("staffListId") long staffListId, @Param("calcDate") LocalDate calcDate);

    @Modifying(clearAutomatically = true)
    @Query("update TimeSheet set actualDaysWorked=:actualDaysWorked where id=:id")
    void updateTimeSheetDay(@Param("id") long id, @Param("actualDaysWorked") int actualDaysWorked);


    @Query("select max(c.calcDate) from TimeSheet t join t.calcSettings c where  t.people.id=:peopleId")
    LocalDate getMaxTimeSheetForPeople(@Param("peopleId") long peopleId);

//    Сделал вместо запроса метод в PeopleService
//    @Query("select concat(p.surName,' ', p.firstName,' ', p.patronymic) from StaffList s join s.people p where s.id=:staffListId")
//    String getPeopleFioByStaffList(@Param("staffListId") long staffListId);

//Нижний метод с запросом
//    @Query("select t from TimeSheet t where t.calcSettings.id=:calcSettingsId")
//    List<TimeSheet> getAllTimeSheetsWithMaxDate(@Param("calcSettingsId") long calcSettingsId);

    List<TimeSheet> findTimeSheetByCalcSettingsEquals(CalcSettings calcSettings);

}
