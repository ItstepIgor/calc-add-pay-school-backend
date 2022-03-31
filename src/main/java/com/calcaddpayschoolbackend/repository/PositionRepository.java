package com.calcaddpayschoolbackend.repository;

import com.calcaddpayschoolbackend.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Position set sorting=:sorting where id=:id")
    void updatePositionSorting(@Param("id") long id, @Param("sorting") int sorting);
}
