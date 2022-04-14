package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.pojo.BonusPojo;
import com.calcaddpayschoolbackend.pojo.ComplicationAndMotivationPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffListRepository extends JpaRepository<StaffList, Long> {

    List<StaffList> findAllByOrderByIdAsc();

    @Query("select st from StaffList st join  st.people p join st.position pos " +
            "where st.disabled=true order by pos.sorting, p.surName")
    List<StaffList> findAllByWhoWorked();

    @Query("select st from StaffList st join  st.people p join st.position pos " +
            "where st.disabled=false order by pos.sorting, p.surName")
    List<StaffList> findAllWhoDidNotWork();

    @Query("select (count (s) > 0) from StaffList s where s.people.id=:peopleId and s.position.id=:positionId")
    boolean isExistStaffList(@Param("peopleId") long peopleId, @Param("positionId") long positionId);


    @Query(value = "SELECT p.sur_name || ' ' || p.first_name || ' ' || p.patronymic AS fio," +
            "posit.position_name AS pos," +
            "psr.sum AS bonussum," +
            "SUM(apr.sum) AS addsum," +
            "STRING_AGG(ap.add_pay_code, ', ') AS cod," +
            "SUM(psr.sum + apr.sum) AS allsum " +
            "FROM staff_list sl LEFT JOIN people p ON sl.people_id = p.id " +
            "LEFT JOIN position posit ON sl.position_id = posit.id " +
            "LEFT JOIN add_pay_result apr ON sl.id = apr.staff_list_id " +
            "LEFT JOIN add_pay ap ON ap.id = apr.add_pay_id " +
            "LEFT JOIN add_pay_type apt on apt.id = ap.add_pay_type_id " +
            "LEFT JOIN percent_salary_result psr ON sl.id = psr.staff_list_id " +
            "LEFT JOIN time_sheet ts on ts.id = apr.time_sheet_id " +
            "LEFT JOIN calc_settings cs on ts.calc_settings_id = cs.id " +
            "WHERE psr.percent <> (SELECT ps.percent_salary_for_young_special " +
            "FROM percent_salary ps WHERE percent_start_date = " +
            "(SELECT MAX(percent_start_date) FROM percent_salary)) " +
            "AND cs.calc_date = (SELECT MAX(calc_date) FROM calc_settings)" +
            "GROUP BY fio, pos, bonussum", nativeQuery = true)
    List<BonusPojo> findByAllBonus();

    @Query(value = "SELECT p.sur_name || ' ' || p.first_name || ' ' || p.patronymic AS fio," +
            "posit.position_name                                      AS pos," +
            "SUM(apr.sum)                                             AS addsum," +
            "STRING_AGG(ap.add_pay_code, ', ')                        AS cod " +
            "FROM staff_list sl LEFT JOIN people p ON sl.people_id = p.id " +
            "LEFT JOIN position posit ON sl.position_id = posit.id " +
            "LEFT JOIN add_pay_result apr ON sl.id = apr.staff_list_id " +
            "LEFT JOIN add_pay ap ON ap.id = apr.add_pay_id " +
            "LEFT JOIN add_pay_type apt on apt.id = ap.add_pay_type_id " +
            "LEFT JOIN time_sheet ts on ts.id = apr.time_sheet_id " +
            "LEFT JOIN calc_settings cs on ts.calc_settings_id = cs.id " +
            "WHERE cs.calc_date = (SELECT MAX(calc_date) FROM calc_settings) AND apt.id = 3 " +
            "GROUP BY fio, pos", nativeQuery = true)
    List<ComplicationAndMotivationPojo> findByAllComplicationAndMotivation();
}
