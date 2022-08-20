package br.com.fiap.soccer.entity;

import br.com.fiap.soccer.dto.CreateUpdateTeamDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_TEAM")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate foundationDate;

    private Integer members;

    public TeamEntity(){}

    public TeamEntity(CreateUpdateTeamDTO createUpdateTeamDTO) {
        this.name = createUpdateTeamDTO.getName();
        this.foundationDate = createUpdateTeamDTO.getFoundationDate();
        this.members = createUpdateTeamDTO.getMembers();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }
}
