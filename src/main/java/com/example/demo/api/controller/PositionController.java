package com.example.demo.api.controller;

import com.example.demo.bll.dto.PositionDto;
import com.example.demo.bll.service.PositionService;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.util.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        List<PositionDto> positions = positionService.getAllPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionDto>> getPositionById(@PathVariable Long id) {
        Optional<PositionDto> positionOptional = positionService.getPositionById(id);
        return positionOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Position with id " + id + " is not found."));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PositionDto>> savePosition(@Valid @RequestBody PositionDto positionDto) {
        PositionDto createdPosition = positionService.createPosition(positionDto);
        return ResponseHelper.successResponse(createdPosition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionDto>> updatePosition(@PathVariable Long id, @RequestBody PositionDto positionDto) {
        Optional<PositionDto> updatedPosition = positionService.updatePosition(id, positionDto);
        return updatedPosition.map(ResponseHelper::successResponse)
                .orElseGet(() -> ResponseHelper.notFoundResponse("Position not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
