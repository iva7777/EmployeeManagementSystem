package com.example.demo.dal.repository;

import com.example.demo.dal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByTeam_ManagerIsNotNull();
    List<Employee> findByGenderAndExperienceYearsBetween(String gender, int minYears, int maxYears);
}
