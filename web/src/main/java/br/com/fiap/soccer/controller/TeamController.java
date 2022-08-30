package br.com.fiap.soccer.controller;

import br.com.fiap.soccer.dto.CreateUpdateTeamDTO;
import br.com.fiap.soccer.dto.TeamDTO;
import br.com.fiap.soccer.dto.TeamFilterDTO;
import br.com.fiap.soccer.dto.UpdateTeamMembersDTO;
import br.com.fiap.soccer.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
//        public List<TeamDTO> list( @RequestParam(required = false) String name) {
    public List<TeamDTO> list( TeamFilterDTO teamFilterDTO) {
        return teamService.listAll(teamFilterDTO.getName());
    }

    @GetMapping("{id}")
    public TeamDTO findById(@PathVariable Long id){
        return teamService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO create(@RequestBody CreateUpdateTeamDTO createUpdateTeamDTO){
        return teamService.create(createUpdateTeamDTO);
    }

    @PutMapping("{id}")
    public TeamDTO update(@PathVariable Long id,
                          @RequestBody CreateUpdateTeamDTO createUpdateTeamDTO){
        return teamService.update(id, createUpdateTeamDTO);
    }

    @PatchMapping("{id}")
    public TeamDTO updateMembers(@PathVariable Long id,
                                 @RequestBody UpdateTeamMembersDTO updateTeamMembersDTO){
        return teamService.updateMembers(id, updateTeamMembersDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        teamService.delete(id);
    }
}
