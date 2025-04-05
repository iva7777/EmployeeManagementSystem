package com.example.demo.bll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PositionDto(
        Long positionId,
        @Size(max = 50, message = "Position's title must be at most 50 characters")
        @NotBlank
        String title,
        @NotNull(message = "Hierarchy level must not be null.")
        int hierarchyLevel
) {
}
