package com.example.demo.bll.mapper;

import com.example.demo.bll.dto.PositionDto;
import com.example.demo.dal.entity.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionDto toDto(Position position);
    Position toEntity(PositionDto positionDto);
}
