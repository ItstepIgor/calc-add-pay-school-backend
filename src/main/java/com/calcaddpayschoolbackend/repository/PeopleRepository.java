package com.calcaddpayschoolbackend.repository;


import com.calcaddpayschoolbackend.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
