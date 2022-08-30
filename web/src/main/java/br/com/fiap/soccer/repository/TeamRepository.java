package br.com.fiap.soccer.repository;

import br.com.fiap.soccer.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    List<TeamEntity> findAllByNameContaining(String name);

}
