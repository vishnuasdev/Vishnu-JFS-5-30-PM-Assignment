package com.example.assignmentthree.repository;


import com.example.assignmentthree.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE j.salary > :salary")
    List<Job> findJobsBySalaryGreaterThan(@Param("salary") double salary);

    @Query("SELECT j FROM Job j WHERE j.location = :location")
    List<Job> findJobsByLocation(@Param("location") String location);

    @Modifying
    @Transactional
    @Query("UPDATE Job j SET j.salary = :salary WHERE j.id = :id")
    int updateJobSalary(@Param("id") Long id, @Param("salary") double salary);

    @Modifying
    @Transactional
    @Query("DELETE FROM Job j WHERE j.company = :company")
    int deleteJobsByCompany(@Param("company") String company);
}