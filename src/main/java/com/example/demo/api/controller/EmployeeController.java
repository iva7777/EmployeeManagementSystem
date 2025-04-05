package com.example.demo.api.controller;

import com.example.demo.bll.dto.EmployeeDto;
import com.example.demo.bll.service.EmployeeService;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.util.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDto> employeeOptional = employeeService.getEmployeeById(id);
        return employeeOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Employee with id " + id + " is not found."));
    }

    @GetMapping("/managers")
    public ResponseEntity<List<EmployeeDto>> getAllDirectManagers() {
        List<EmployeeDto> managers = employeeService.getAllDirectManagers();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/experiencedFemales")
    public ResponseEntity<List<EmployeeDto>> getExperiencedFemaleEmployees() {
        List<EmployeeDto> experiencedFemales = employeeService.getExperiencedFemaleEmployees();
        return new ResponseEntity<>(experiencedFemales, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDto>> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseHelper.successResponse(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Optional<EmployeeDto> updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return updatedEmployee.map(ResponseHelper::successResponse)
                .orElseGet(() -> ResponseHelper.notFoundResponse("Employee not found."));
    }

    @PutMapping("/{employeeId}/promote/{newPositionId}")
    public ResponseEntity<ApiResponse<EmployeeDto>> promoteEmployee(
            @PathVariable Long employeeId,
            @PathVariable Long newPositionId
    ) {
        return employeeService.promoteEmployee(employeeId, newPositionId)
                .map(ResponseHelper::successResponse)
                .orElseGet(() -> ResponseHelper
                        .failureResponse("Promotion failed. Check if the employee or position exists, or if the position level is appropriate."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
