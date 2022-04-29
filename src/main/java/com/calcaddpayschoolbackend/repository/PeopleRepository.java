package com.calcaddpayschoolbackend.repository;


import com.calcaddpayschoolbackend.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    List<People> findAllByOrderByIdAsc();

    @Query("select p, min (pos.sorting) as sort from People p join  p.staffLists st join st.position pos " +
            "where st.disabled=true group by p order by sort, p.surName")
    List<People> findAllByWhoWorked();
}
