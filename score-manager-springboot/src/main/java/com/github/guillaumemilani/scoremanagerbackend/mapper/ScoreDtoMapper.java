package com.github.guillaumemilani.scoremanagerbackend.mapper;

import com.github.guillaumemilani.scoremanagerbackend.model.Score;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface ScoreDtoMapper {
    ScoreDtoMapper INSTANCE = Mappers.getMapper(ScoreDtoMapper.class);

    com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto toDto(Score score);

    com.github.guillaumemilani.scoremanagerbackend.api.model.ScoresDto toScores(Page<Score> scores);
}
