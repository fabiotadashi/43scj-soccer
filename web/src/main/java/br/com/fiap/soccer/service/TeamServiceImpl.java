package br.com.fiap.soccer.service;

import br.com.fiap.soccer.dto.CreateUpdateTeamDTO;
import br.com.fiap.soccer.dto.TeamDTO;
import br.com.fiap.soccer.dto.UpdateTeamMembersDTO;
import br.com.fiap.soccer.entity.TeamEntity;
import br.com.fiap.soccer.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private ObjectMapper objectMapper;

    public TeamServiceImpl(TeamRepository teamRepository,
                           ObjectMapper objectMapper) {
        this.teamRepository = teamRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TeamDTO create(CreateUpdateTeamDTO createUpdateTeamDTO) {
        TeamEntity teamEntity = new TeamEntity(createUpdateTeamDTO);
        TeamEntity savedTeamEntity = teamRepository.save(teamEntity);
        return new TeamDTO(savedTeamEntity);
    }

    @Override
    public TeamDTO findById(Long id) {
        TeamEntity teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TeamDTO teamDTO = objectMapper.convertValue(teamEntity, TeamDTO.class);
        return teamDTO;
    }

    @Override
    public List<TeamDTO> listAll(String nome) {
        List<TeamEntity> list;
        if (nome == null) {
            list = teamRepository.findAll();
        } else {
            list = teamRepository.findAllByNameContaining(nome);
        }
        return list.stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO update(Long id, CreateUpdateTeamDTO createUpdateTeamDTO) {
        TeamEntity teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        teamEntity.setName(createUpdateTeamDTO.getName());
        teamEntity.setMembers(createUpdateTeamDTO.getMembers());
        teamEntity.setFoundationDate(createUpdateTeamDTO.getFoundationDate());

        TeamEntity savedEntity = teamRepository.save(teamEntity);
        return new TeamDTO(savedEntity);
    }

    @Override
    public TeamDTO updateMembers(Long id, UpdateTeamMembersDTO updateTeamMembersDTO) {
        TeamEntity teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        teamEntity.setMembers(updateTeamMembersDTO.getMembers());

        TeamEntity savedEntity = teamRepository.save(teamEntity);
        return new TeamDTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
