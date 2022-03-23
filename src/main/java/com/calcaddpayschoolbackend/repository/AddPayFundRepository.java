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
    List<AddPayFund> getAddPayCurrentFunds(@Param("date") LocalDate date);


    @Query("select max(c.calcDate) from AddPayFund af join af.calcSettings c where af.addPayTypes.id=:addPayTypeId")
    LocalDate getAddPayFundMaxDate(@Param("addPayTypeId") long addPayTypeId);

}

//    select max(cs.calc_date) from add_pay_fund apf
//    left join calc_settings cs on cs.id = apf.calc_settings_id
//        where apf.add_pay_type_id=3;