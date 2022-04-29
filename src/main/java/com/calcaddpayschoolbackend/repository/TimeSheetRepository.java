package com.calcaddpayschoolbackend.repository;

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

    //    @Query("select ts from TimeSheet ts join ts.calcSettings cs join ts.people p " +
//            "join p.staffLists st join st.position pos order by cs.calcDate, pos.sorting, p.surName")
    @Query("select ts, min(pos.sorting) as sort from TimeSheet ts join ts.calcSettings cs join ts.people p " +
            "join p.staffLists st join st.position pos group by ts, p.surName order by sort, p.surName  ")
    List<TimeSheet> findAllSortingByPosition();


//Нижний метод с запросом
//    @Query("select t from TimeSheet t where t.calcSettings.id=:calcSettingsId")
//    List<TimeSheet> getAllTimeSheetsWithMaxDate(@Param("calcSettingsId") long calcSettingsId);

    @Query("select ts, min(pos.sorting) as sort from TimeSheet ts join ts.calcSettings cs join ts.people p " +
            "join p.staffLists st join st.position pos where cs.calcDate=:calcDate group by ts, p.surName " +
            "order by sort, p.surName")
    List<TimeSheet> findTimeSheetByCalcDate(@Param("calcDate") LocalDate calcDate);

}
