package com.github.guillaumemilani.scoremanagerbackend.mapper;

import com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto;
import com.github.guillaumemilani.scoremanagerbackend.model.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface ScoreDtoMapper {
    ScoreDtoMapper INSTANCE = Mappers.getMapper(ScoreDtoMapper.class);

    @Mapping(target = "duration", source = "durationSeconds")
    com.github.guillaumemilani.scoremanagerbackend.api.model.ScoreDto toDto(Score score);

    com.github.guillaumemilani.scoremanagerbackend.api.model.ScoresDto toScores(Page<Score> scores);

    @Mapping(target = "durationSeconds", source = "duration")
    Score toScore(ScoreDto dto);
}
