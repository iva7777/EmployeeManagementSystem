package com.example.demo.bll.service;

import com.example.demo.bll.dto.EmployeeDto;
import com.example.demo.dal.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> getAllDirectManagers();
    List<EmployeeDto> getExperiencedFemaleEmployees();
    Optional<EmployeeDto> promoteEmployee(Long employeeId, Long newPositionId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    Optional<EmployeeDto> getEmployeeById(Long id);
    Optional<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
