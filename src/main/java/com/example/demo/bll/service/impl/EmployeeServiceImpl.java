package com.example.demo.bll.service.impl;

import com.example.demo.bll.dto.EmployeeDto;
import com.example.demo.bll.mapper.EmployeeMapper;
import com.example.demo.bll.service.EmployeeService;
import com.example.demo.dal.entity.Employee;
import com.example.demo.dal.entity.Position;
import com.example.demo.dal.entity.Team;
import com.example.demo.dal.repository.EmployeeRepository;
import com.example.demo.dal.repository.PositionRepository;
import com.example.demo.dal.repository.TeamRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final TeamRepository teamRepository;
    private final PositionRepository positionRepository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(@NotNull EmployeeRepository repository, TeamRepository teamRepository, @NotNull PositionRepository positionRepository, @NotNull EmployeeMapper mapper) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EmployeeDto> getAllDirectManagers() {
        List<Employee> managers = repository.findByTeam_ManagerIsNotNull();
        return managers.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getExperiencedFemaleEmployees() {
        List<Employee> experiencedFemales = repository.findByGenderAndExperienceYearsBetween("female", 10, 15);
        return experiencedFemales.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<EmployeeDto> promoteEmployee(Long employeeId, Long newPositionId) {
        Optional<Employee> optionalEmployee = repository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }

        Employee employee = optionalEmployee.get();

        Optional<Position> optionalPosition = positionRepository.findById(newPositionId);
        if (optionalPosition.isEmpty()) {
            return Optional.empty();
        }

        Position newPosition = optionalPosition.get();

        if (newPosition.getHierarchyLevel() <= employee.getPosition().getHierarchyLevel()) {
            return Optional.empty();
        }

        employee.setPosition(newPosition);
        Employee updatedEmployee = repository.save(employee);

        return Optional.of(mapper.toDto(updatedEmployee));
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapper.toEntity(employeeDto);

        if (employeeDto.teamId() != null) {
            teamRepository.findById(employeeDto.teamId()).ifPresent(employee::setTeam);
        }


        if (employeeDto.positionId() != null) {
            positionRepository.findById(employeeDto.positionId()).ifPresent(employee::setPosition);
        }


        if (employee.getTeam() != null) {
            Team team = employee.getTeam();
            if (team.getEmployees() == null) {
                team.setEmployees(new ArrayList<>());
            }
            team.getEmployees().add(employee);
        }

        return mapper.toDto(repository.save(employee));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> getEmployeeById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public Optional<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) {
        return repository.findById(id).map(existingEmployee -> {
            existingEmployee.setFirstName(employeeDto.firstName());
            existingEmployee.setLastName(employeeDto.lastName());
            existingEmployee.setGender(employeeDto.gender());
            existingEmployee.setExperienceYears(employeeDto.experienceYears());

            if (employeeDto.teamId() != null) {
                teamRepository.findById(employeeDto.teamId()).ifPresent(existingEmployee::setTeam);
            }

            if (employeeDto.positionId() != null) {
                positionRepository.findById(employeeDto.positionId()).ifPresent(existingEmployee::setPosition);
            }

            Employee updatedEmployee = repository.save(existingEmployee);
            return mapper.toDto(updatedEmployee);
        });
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
