package com.github.guillaumemilani.scoremanagerbackend.controller;

import com.github.guillaumemilani.scoremanagerbackend.api.ScoresApi;
import com.github.guillaumemilani.scoremanagerbackend.api.model.ScoresDto;
import com.github.guillaumemilani.scoremanagerbackend.mapper.ScoreDtoMapper;
import com.github.guillaumemilani.scoremanagerbackend.repository.ScoreRepository;
import com.github.guillaumemilani.scoremanagerbackend.repository.ScoreSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ScoresController implements ScoresApi {
    public static final ScoreDtoMapper MAPPER = ScoreDtoMapper.INSTANCE;
    private ScoreRepository scoreRepository;

    @Override
    public ResponseEntity<com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto> addScore(com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto score) {
        return null;
    }

    @Override
    public ResponseEntity<ScoresDto> getAllScores(Integer page, Integer size, String sort, String direction, String search) {
        return ResponseEntity.ok(MAPPER.toScores(scoreRepository.findAll(
                ScoreSpecs.search(search),
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort)))
        ));
    }
}
