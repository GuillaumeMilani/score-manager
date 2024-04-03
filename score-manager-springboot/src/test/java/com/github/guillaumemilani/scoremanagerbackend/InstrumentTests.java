package com.github.guillaumemilani.scoremanagerbackend;

import com.github.guillaumemilani.scoremanagerbackend.model.Instrument;
import com.github.guillaumemilani.scoremanagerbackend.repository.InstrumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InstrumentTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @AfterEach
    void tearDown() {
        this.instrumentRepository.deleteAll();
    }

    @Test
    void shouldListInstruments() throws Exception {
        this.mockMvc.perform(get("/instruments")).andExpect(status().isOk());
        Instrument instrument = new Instrument();
        instrument.setName("My instrument");
        instrumentRepository.save(instrument);
        this.mockMvc.perform(get("/instruments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("My instrument"));
    }
}
