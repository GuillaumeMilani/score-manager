package com.github.guillaumemilani.scoremanagerbackend.controller;

import com.github.guillaumemilani.scoremanagerbackend.MyPageRequest;
import com.github.guillaumemilani.scoremanagerbackend.api.ScoresApi;
import com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto;
import com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreFormDto;
import com.github.guillaumemilani.scoremanagerbackend.api.model.ScoresDto;
import com.github.guillaumemilani.scoremanagerbackend.mapper.ScoreDtoMapper;
import com.github.guillaumemilani.scoremanagerbackend.repository.ScoreRepository;
import com.github.guillaumemilani.scoremanagerbackend.repository.ScoreSpecs;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ScoresController implements ScoresApi {
    public static final ScoreDtoMapper MAPPER = ScoreDtoMapper.INSTANCE;
    private ScoreRepository scoreRepository;

    @Override
    public ResponseEntity<com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto> addScore(com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreFormDto score) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MAPPER.toDto(scoreRepository.save(MAPPER.toScore(score))));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteScoreById(Long id) {
        // TODO Check if we can delete the score
        scoreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ScoresDto> getAllScores(Integer page, Integer size, String sort, String direction, String search) {
        return ResponseEntity.ok(MAPPER.toScores(scoreRepository.findAll(
                ScoreSpecs.search(search),
                MyPageRequest.of(page, size, sort, direction)
        )));
    }

    @Override
    public ResponseEntity<ScoreDto> getScoreById(Long id) {
        return scoreRepository.findById(id).map(MAPPER::toDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ScoreDto> updateScoreById(Long id, ScoreFormDto scoreFormDto) {
        return ResponseEntity.ok(MAPPER.toDto(scoreRepository.save(MAPPER.toScore(scoreFormDto))));
    }
}
