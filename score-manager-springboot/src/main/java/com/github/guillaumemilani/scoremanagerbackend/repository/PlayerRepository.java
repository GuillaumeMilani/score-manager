package com.github.guillaumemilani.scoremanagerbackend.repository;

import com.github.guillaumemilani.scoremanagerbackend.model.Player;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>, JpaSpecificationExecutor<Player> {
}
