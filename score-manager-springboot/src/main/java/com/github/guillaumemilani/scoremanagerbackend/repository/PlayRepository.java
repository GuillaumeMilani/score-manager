package com.github.guillaumemilani.scoremanagerbackend.repository;

import com.github.guillaumemilani.scoremanagerbackend.model.Play;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends CrudRepository<Play, Long> {
}
