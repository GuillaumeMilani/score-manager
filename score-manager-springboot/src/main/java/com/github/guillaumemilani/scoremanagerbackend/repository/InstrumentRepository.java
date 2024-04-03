package com.github.guillaumemilani.scoremanagerbackend.repository;

import com.github.guillaumemilani.scoremanagerbackend.model.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends CrudRepository<Instrument, Long>, PagingAndSortingRepository<Instrument, Long> {
}
