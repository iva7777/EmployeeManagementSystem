package com.example.demo.bll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeDto(
        Long employeeId,
        @Size(max = 50, message = "First Name must be at most 50 characters")
        @NotBlank
        String firstName,
        @Size(max = 50, message = "Last Name must be at most 50 characters")
        @NotBlank
        String lastName,
        @NotBlank
        String gender,
        @NotNull(message = "Experience years cannot be null.")
        int experienceYears,
        Long positionId,
        Long teamId
) {
}
