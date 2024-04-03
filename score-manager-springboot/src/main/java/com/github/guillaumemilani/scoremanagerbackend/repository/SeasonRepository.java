package com.github.guillaumemilani.scoremanagerbackend.repository;

import com.github.guillaumemilani.scoremanagerbackend.model.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends CrudRepository<Season, Long> {
}
