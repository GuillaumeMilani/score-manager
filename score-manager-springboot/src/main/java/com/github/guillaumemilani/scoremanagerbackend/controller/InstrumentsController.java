package com.github.guillaumemilani.scoremanagerbackend.controller;

import com.github.guillaumemilani.scoremanagerbackend.api.model.InstrumentDto;
import com.github.guillaumemilani.scoremanagerbackend.api.model.InstrumentsDto;
import com.github.guillaumemilani.scoremanagerbackend.mapper.InstrumentDtoMapper;
import com.github.guillaumemilani.scoremanagerbackend.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class InstrumentsController implements com.github.guillaumemilani.scoremanagerbackend.api.InstrumentsApi {
    private InstrumentRepository instrumentRepository;

    @Override
    public ResponseEntity<InstrumentDto> addInstrument(InstrumentDto instrumentDto) {
        return null;
    }

    @Override
    public ResponseEntity<InstrumentsDto> getAllInstruments() {
        return ResponseEntity.ok(InstrumentDtoMapper.INSTANCE.toInstruments(instrumentRepository.findAll(Pageable.unpaged())));
    }
}
