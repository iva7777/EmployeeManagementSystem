package com.example.demo.bll.mapper;

import com.example.demo.bll.dto.TeamDto;
import com.example.demo.dal.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(source = "manager.employeeId", target = "managerId")
    TeamDto toDto(Team team);

    @Mapping(target = "manager", ignore = true)
    Team toEntity(TeamDto teamDto);
}
