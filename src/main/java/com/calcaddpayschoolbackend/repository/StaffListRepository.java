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


    @Query(value = "SELECT p.sur_name || ' ' || p.first_name || ' ' || p.patronymic AS fio, " +
            "       posit.position_name                                      AS pos, " +
            "       psr.sum                                                  AS bonussum, " +
            "       COALESCE((SELECT sum(sum) " +
            "                 FROM add_pay_result apr2 " +
            "                          LEFT JOIN add_pay ap2 ON ap2.id = apr2.add_pay_id " +
            "                          LEFT JOIN add_pay_type apt2 on apt2.id = ap2.add_pay_type_id " +
            "                 where apt2.id = :addPayTypeId " +
            "                   and apr2.time_sheet_id = psr.time_sheet_id " +
            "                 group by apr2.time_sheet_id), 0) AS addsum, " +
            "       COALESCE((SELECT STRING_AGG(ap.add_pay_code, ', ') " +
            "                 FROM add_pay_result apr2 " +
            "                          LEFT JOIN add_pay ap2 ON ap2.id = apr2.add_pay_id " +
            "                          LEFT JOIN add_pay_type apt2 on apt2.id = ap2.add_pay_type_id " +
            "                 where apt2.id = :addPayTypeId " +
            "                   and apr2.time_sheet_id = psr.time_sheet_id " +
            "                 group by apr2.time_sheet_id), '') AS cod " +
            "FROM percent_salary_result psr " +
            "         LEFT JOIN time_sheet ts on ts.id = psr.time_sheet_id " +
            "         LEFT JOIN staff_list sl on sl.id = ts.staff_list_id " +
            "         LEFT JOIN people p ON sl.people_id = p.id " +
            "         LEFT JOIN position posit ON sl.position_id = posit.id " +
            "         LEFT JOIN add_pay_result apr ON ts.id = apr.time_sheet_id " +
            "         LEFT JOIN add_pay ap ON ap.id = apr.add_pay_id " +
            "         LEFT JOIN add_pay_type apt on apt.id = ap.add_pay_type_id " +
            "         LEFT JOIN calc_settings cs on ts.calc_settings_id = cs.id " +
            "WHERE psr.percent <> (SELECT ps.percent_salary_for_young_special " +
            "FROM percent_salary ps WHERE percent_start_date = " +
            "(SELECT MAX(percent_start_date) FROM percent_salary)) " +
            "AND cs.calc_date = (SELECT MAX(calc_date) FROM calc_settings)" +
            "GROUP BY fio, pos, bonussum, posit.sorting, psr.time_sheet_id " +
            "ORDER BY posit.sorting, fio", nativeQuery = true)
    List<BonusPojo> findByAllBonus(@Param("addPayTypeId") long addPayTypeId);

    @Query(value = "SELECT p.sur_name || ' ' || p.first_name || ' ' || p.patronymic AS fio," +
            "posit.position_name                                      AS pos," +
            "SUM(apr.sum)                                             AS addsum," +
            "STRING_AGG(ap.add_pay_code, ', ')                        AS cod " +
            "FROM staff_list sl LEFT JOIN people p ON sl.people_id = p.id " +
            "LEFT JOIN position posit ON sl.position_id = posit.id " +
            "LEFT JOIN time_sheet ts on sl.id = ts.staff_list_id " +
            "LEFT JOIN add_pay_result apr ON ts.id = apr.time_sheet_id " +
            "LEFT JOIN add_pay ap ON ap.id = apr.add_pay_id " +
            "LEFT JOIN add_pay_type apt on apt.id = ap.add_pay_type_id " +
            "LEFT JOIN calc_settings cs on ts.calc_settings_id = cs.id " +
            "WHERE cs.calc_date = (SELECT MAX(calc_date) FROM calc_settings) AND apt.id = :addPayTypeId " +
            "GROUP BY fio, pos", nativeQuery = true)
    List<ComplicationAndMotivationPojo> findByAllComplication(@Param("addPayTypeId") long addPayTypeId);

    @Query(value = "SELECT compl.fio, " +
            "       compl.pos, " +
            "       sum(compl.addsum) AS addsum, " +
            "       STRING_AGG(compl.cod, ', ') as cod " +
            "FROM (SELECT p.sur_name || ' ' || p.first_name || ' ' || p.patronymic AS fio, " +
            "             posit.position_name                                      AS pos, " +
            "             psr.sum                                                  AS addsum, " +
            "             (SELECT ps.percent_young_special_code " +
            "              FROM percent_salary ps " +
            "              WHERE ps.percent_start_date = (SELECT MAX(percent_start_date) " +
            "                                             FROM percent_salary))    as cod " +
            "      FROM percent_salary_result psr " +
            "               LEFT JOIN time_sheet ts on ts.id = psr.time_sheet_id " +
            "               LEFT JOIN staff_list sl on sl.id = ts.staff_list_id " +
            "               LEFT JOIN people p ON sl.people_id = p.id\n" +
            "               LEFT JOIN position posit ON sl.position_id = posit.id " +
            "               LEFT JOIN calc_settings cs on ts.calc_settings_id = cs.id " +
            "      WHERE psr.percent = (SELECT ps.percent_salary_for_young_special " +
            "                           FROM percent_salary ps " +
            "                           WHERE percent_start_date = " +
            "                                 (SELECT MAX(percent_start_date) FROM percent_salary)) " +
            "        AND cs.calc_date = (SELECT MAX(calc_date) FROM calc_settings) " +
            "      UNION " +
            "      SELECT p.sur_name || ' ' || p.first_name || ' ' || p.patronymic AS fio, " +
            "             posit.position_name                                      AS pos, " +
            "             SUM(apr.sum)                                             AS addsum, " +
            "             STRING_AGG(ap.add_pay_code, ', ')                        as cod " +
            "      FROM add_pay_result apr " +
            "               LEFT JOIN time_sheet ts on ts.id = apr.time_sheet_id " +
            "               LEFT JOIN staff_list sl on sl.id = ts.staff_list_id " +
            "               LEFT JOIN people p ON sl.people_id = p.id " +
            "               LEFT JOIN position posit ON sl.position_id = posit.id " +
            "               LEFT JOIN add_pay ap ON ap.id = apr.add_pay_id " +
            "               LEFT JOIN add_pay_type apt on apt.id = ap.add_pay_type_id " +
            "               LEFT JOIN calc_settings cs on ts.calc_settings_id = cs.id " +
            "      WHERE cs.calc_date = (SELECT MAX(calc_date) FROM calc_settings) " +
            "        AND apt.id = :addPayTypeId" +
            "      GROUP BY fio, pos) AS compl " +
            "GROUP BY fio, pos", nativeQuery = true)
    List<ComplicationAndMotivationPojo> findByAllMotivation(@Param("addPayTypeId") long addPayTypeId);
}
