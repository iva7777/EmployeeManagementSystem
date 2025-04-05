package com.example.demo.bll.service;

import com.example.demo.bll.dto.PositionDto;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    List<PositionDto> getAllPositions();
    Optional<PositionDto> getPositionById(Long id);
    PositionDto createPosition(PositionDto positionDto);
    Optional<PositionDto> updatePosition(Long id, PositionDto positionDto);
    void deletePosition(Long id);
}
