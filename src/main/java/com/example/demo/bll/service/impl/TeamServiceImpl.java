package com.example.demo.bll.service.impl;

import com.example.demo.bll.dto.TeamDto;
import com.example.demo.bll.mapper.TeamMapper;
import com.example.demo.bll.service.TeamService;
import com.example.demo.dal.entity.Position;
import com.example.demo.dal.entity.Team;
import com.example.demo.dal.repository.EmployeeRepository;
import com.example.demo.dal.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    private final EmployeeRepository employeeRepository;
    private final TeamMapper mapper;

    public TeamServiceImpl(TeamRepository repository, EmployeeRepository employeeRepository, TeamMapper mapper) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TeamDto> getTeamById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team = mapper.toEntity(teamDto);
        return mapper.toDto(repository.save(team));
    }

    @Override
    public Optional<TeamDto> updateTeam(Long id, TeamDto teamDto) {
        return repository.findById(id).map(existingTeam -> {
            existingTeam.setTeamName(teamDto.teamName());

            if (teamDto.managerId() != null) {
                employeeRepository.findById(teamDto.managerId())
                        .ifPresent(existingTeam::setManager);
            } else {
                existingTeam.setManager(null);
            }

            Team updatedTeam = repository.save(existingTeam);
            return mapper.toDto(updatedTeam);
        });
    }

    @Override
    public void deleteTeam(Long id) {
        repository.deleteById(id);
    }
}
