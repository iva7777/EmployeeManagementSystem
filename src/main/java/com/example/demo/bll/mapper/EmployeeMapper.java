package com.example.demo.bll.mapper;

import com.example.demo.bll.dto.EmployeeDto;
import com.example.demo.dal.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "position.positionId", target = "positionId")
    @Mapping(source = "team.teamId", target = "teamId")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "position", ignore = true)
    @Mapping(target = "team", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);
}
