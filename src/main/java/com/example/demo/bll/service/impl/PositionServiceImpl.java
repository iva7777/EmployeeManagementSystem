package com.example.demo.bll.service.impl;

import com.example.demo.bll.dto.PositionDto;
import com.example.demo.bll.mapper.PositionMapper;
import com.example.demo.bll.service.PositionService;
import com.example.demo.dal.entity.Position;
import com.example.demo.dal.repository.PositionRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository repository;
    private final PositionMapper mapper;

    public PositionServiceImpl(@NotNull PositionRepository repository, @NotNull PositionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PositionDto> getAllPositions() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PositionDto> getPositionById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public PositionDto createPosition(PositionDto positionDto) {
        Position position = mapper.toEntity(positionDto);
        return mapper.toDto(repository.save(position));
    }

    @Override
    public Optional<PositionDto> updatePosition(Long id, PositionDto positionDto) {
        return repository.findById(id).map(existingPosition -> {
            existingPosition.setTitle(positionDto.title());
            existingPosition.setHierarchyLevel(positionDto.hierarchyLevel());

            Position updatedPosition = repository.save(existingPosition);
            return mapper.toDto(updatedPosition);
        });
    }

    @Override
    public void deletePosition(Long id) {
        repository.deleteById(id);
    }
}
