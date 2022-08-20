package br.com.fiap.soccer.service;

import br.com.fiap.soccer.dto.CreateUpdateTeamDTO;
import br.com.fiap.soccer.dto.TeamDTO;
import br.com.fiap.soccer.dto.UpdateTeamMembersDTO;

import java.util.List;

public interface TeamService {

    TeamDTO create(CreateUpdateTeamDTO createUpdateTeamDTO);
    TeamDTO findById(Long id);
    List<TeamDTO> listAll(String nome);
    TeamDTO update(Long id, CreateUpdateTeamDTO createUpdateTeamDTO);
    TeamDTO updateMembers(Long id, UpdateTeamMembersDTO updateTeamMembersDTO);
    void delete(Long id);

}
