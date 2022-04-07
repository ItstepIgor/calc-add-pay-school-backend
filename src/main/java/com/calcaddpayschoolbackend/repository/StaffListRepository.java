package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.StaffList;
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
}

//    select count(s) > 0
//        from staff_list s
//        where people_id = 3
//        and position_id = 3;
