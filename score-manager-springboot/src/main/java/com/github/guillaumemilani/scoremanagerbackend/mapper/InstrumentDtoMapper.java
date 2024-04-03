package com.github.guillaumemilani.scoremanagerbackend.mapper;

import com.github.guillaumemilani.scoremanagerbackend.model.Instrument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface InstrumentDtoMapper {
    InstrumentDtoMapper INSTANCE = Mappers.getMapper(InstrumentDtoMapper.class);

    com.github.guillaumemilani.scoremanagerbackend.api.model.InstrumentDto toDto(Instrument instrument);

    com.github.guillaumemilani.scoremanagerbackend.api.model.InstrumentsDto toInstruments(Page<Instrument> instruments);
}
