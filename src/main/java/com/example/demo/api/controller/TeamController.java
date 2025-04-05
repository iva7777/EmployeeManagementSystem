package com.example.demo.api.controller;

import com.example.demo.bll.dto.PositionDto;
import com.example.demo.bll.dto.TeamDto;
import com.example.demo.bll.service.TeamService;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.util.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamDto>> getTeamById(@PathVariable Long id) {
        Optional<TeamDto> teamOptional = teamService.getTeamById(id);
        return teamOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Team with id " + id + " is not found."));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TeamDto>> saveTeam(@Valid @RequestBody TeamDto teamDto) {
        TeamDto createdTeam = teamService.createTeam(teamDto);
        return ResponseHelper.successResponse(createdTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamDto>> updateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) {
        Optional<TeamDto> updatedTeam = teamService.updateTeam(id, teamDto);
        return updatedTeam.map(ResponseHelper::successResponse)
                .orElseGet(() -> ResponseHelper.notFoundResponse("Team not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
