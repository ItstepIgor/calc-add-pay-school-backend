package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPayResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AddPayResultRepository extends JpaRepository<AddPayResult, Long> {

    @Query("select sum(asr.sum) from AddPayResult asr join asr.addPay  ap join ap.addPayTypes apt join " +
            "asr.timeSheets ts join ts.calcSettings cs where apt.id=:id and cs.calcDate=:calcDate")
    BigDecimal getAllSumForMonth(@Param("id") long id, @Param("calcDate") LocalDate calcDate);

    @Query("select asr from AddPayResult asr join asr.timeSheets ts join ts.calcSettings cs where cs.calcDate=:calcDate")
    List<AddPayResult> getAllAddPayResultForMonth(@Param("calcDate") LocalDate calcDate);

}


//    select sum(apr.sum)
//    from add_pay_result apr
//        left join add_pay ap on ap.id = apr.add_pay_id
//        left join add_pay_type apt on apt.id = ap.add_pay_type_id
//        left join time_sheet ts on apr.time_sheet_id = ts.id
//        left join calc_settings cs on ts.calc_settings_id = cs.id
//        where apt.id = 3
//        and cs.calc_date = '2022-02-28';