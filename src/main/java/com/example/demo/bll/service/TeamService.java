package com.example.demo.bll.service;

import com.example.demo.bll.dto.TeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<TeamDto> getAllTeams();
    Optional<TeamDto> getTeamById(Long id);
    TeamDto createTeam(TeamDto teamDto);
    Optional<TeamDto> updateTeam(Long id, TeamDto teamDto);
    void deleteTeam(Long id);
}
