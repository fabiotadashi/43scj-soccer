package br.com.fiap.soccer.controller;

import br.com.fiap.soccer.dto.CreateUpdateTeamDTO;
import br.com.fiap.soccer.dto.TeamDTO;
import br.com.fiap.soccer.dto.TeamFilterDTO;
import br.com.fiap.soccer.dto.UpdateTeamMembersDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teams")
public class TeamController {

    private List<TeamDTO> list = new ArrayList<>();

    public TeamController() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(1L);
        teamDTO.setName("Corinthians");
        teamDTO.setFoundationDate(LocalDate.of(1910, 01, 01));
        teamDTO.setMembers(30_000_000);
        list.add(teamDTO);

        TeamDTO teamDTO1 = new TeamDTO();
        teamDTO1.setId(2L);
        teamDTO1.setName("Flamengo");
        teamDTO1.setFoundationDate(LocalDate.of(1900, 02, 10));
        teamDTO1.setMembers(31_000_000);
        list.add(teamDTO1);
    }

    @GetMapping
//        public List<TeamDTO> list( @RequestParam(required = false) String name) {
    public List<TeamDTO> list( TeamFilterDTO teamFilterDTO) {
        return list.stream()
                .filter(teamDTO -> teamFilterDTO.getName() == null || teamDTO.getName().contains(teamFilterDTO.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TeamDTO findById(@PathVariable Long id){
        return list.stream()
                .filter(teamDTO -> teamDTO.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public TeamDTO create(@RequestBody CreateUpdateTeamDTO createUpdateTeamDTO){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(list.size() + 1L);
        teamDTO.setName(createUpdateTeamDTO.getName());
        teamDTO.setMembers(createUpdateTeamDTO.getMembers());
        teamDTO.setFoundationDate(createUpdateTeamDTO.getFoundationDate());

        list.add(teamDTO);
        return teamDTO;
    }

    @PutMapping("{id}")
    public TeamDTO update(@PathVariable Long id,
                          @RequestBody CreateUpdateTeamDTO createUpdateTeamDTO){
        TeamDTO teamDTO = findById(id);
        teamDTO.setName(createUpdateTeamDTO.getName());
        teamDTO.setMembers(createUpdateTeamDTO.getMembers());
        teamDTO.setFoundationDate(createUpdateTeamDTO.getFoundationDate());

        return teamDTO;
    }

    @PatchMapping("{id}")
    public TeamDTO updateMembers(@PathVariable Long id,
                                 @RequestBody UpdateTeamMembersDTO updateTeamMembersDTO){
        TeamDTO teamDTO = findById(id);
        teamDTO.setMembers(updateTeamMembersDTO.getMembers());

        return teamDTO;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        TeamDTO teamDTO = findById(id);
        list.remove(teamDTO);
    }
}
