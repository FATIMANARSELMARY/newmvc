package com.bpo.jiffy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.ScrumMaster;

public interface ScrumMasterEmployeeRepository extends JpaRepository<Employee, Long> {

    // Fetch all employees for a given ScrumMaster
    List<Employee> findByScrumMaster(ScrumMaster scrumMaster);

    // Fetch all ScrumMaster-Employee pairs
    @Query("SELECT e FROM Employee e JOIN e.scrumMaster sm")
    List<Employee> findAllScrumMasterEmployeePairs();
}