package com.github.guillaumemilani.scoremanagerbackend.controller;

import com.github.guillaumemilani.scoremanagerbackend.api.ScoresApi;
import com.github.guillaumemilani.scoremanagerbackend.mapper.ScoreDtoMapper;
import com.github.guillaumemilani.scoremanagerbackend.repository.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ScoresController implements ScoresApi {
    private ScoreRepository scoreRepository;

    @Override
    public ResponseEntity<com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto> addScore(com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto score) {
        return null;
    }

    @Override
    public ResponseEntity<com.github.guillaumemilani.scoremanagerbackend.api.model.ScoresDto> getAllScores() {
        return ResponseEntity.ok(ScoreDtoMapper.INSTANCE.toScores(scoreRepository.findAll(Pageable.unpaged())));
    }
}
