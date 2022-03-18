package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.AddPayResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface AddPayResultRepository extends JpaRepository<AddPayResult, Long> {

    @Query("select sum(asr.sum) from AddPayResult asr join asr.addPay  ap join ap.addPayTypes apt " +
            "where apt.id=:id")
    BigDecimal getAllSumForMonth(@Param("id") long id);

}


//    select sum(apr.sum)
//    from add_pay_result apr
//        left join add_pay ap on ap.id = apr.add_pay_id
//        left join add_pay_type apt on apt.id = ap.add_pay_type_id
//        where apt.id = 3;