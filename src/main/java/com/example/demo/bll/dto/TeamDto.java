package com.example.demo.bll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeamDto(
        Long teamId,
        @NotBlank
        @Size(max = 50, message = "Team name must be at most 50 characters")
        String teamName,
        Long managerId
) {
}
