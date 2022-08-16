package br.com.fiap.soccer.controller;

import br.com.fiap.soccer.dto.TeamDTO;
import br.com.fiap.soccer.dto.TeamFilterDTO;
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
                .filter(teamDTO -> teamDTO.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
