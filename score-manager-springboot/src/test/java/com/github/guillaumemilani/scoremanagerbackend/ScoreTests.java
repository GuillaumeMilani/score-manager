package com.github.guillaumemilani.scoremanagerbackend;

import com.github.guillaumemilani.scoremanagerbackend.model.Instrument;
import com.github.guillaumemilani.scoremanagerbackend.model.Score;
import com.github.guillaumemilani.scoremanagerbackend.repository.InstrumentRepository;
import com.github.guillaumemilani.scoremanagerbackend.repository.ScoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoreTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @AfterEach
    void tearDown() {
        this.scoreRepository.deleteAll();
        this.instrumentRepository.deleteAll();
    }

    @Test
    void shouldListScores() throws Exception {

        mockMvc.perform(get("/scores")
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                )
                .andExpect(status().isOk());
        var instrument = new Instrument();
        instrument.setName("Trombone");
        instrumentRepository.save(instrument);
        var score = new Score();
        score.setTitle("Devil's Tower");
        scoreRepository.save(score);

        this.mockMvc.perform(get("/scores")
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Devil's Tower"));
    }

    @Test
    void shouldCreateScore() throws Exception {
        assertThat(scoreRepository.findAll()).extracting(Score::getTitle).doesNotContain("Devil's Tower");

        this.mockMvc.perform(post("/scores")
                        .content("{\"title\": \"Devil's Tower\"}")
                        .contentType("application/json")
                )
                .andExpect(status().isCreated());

        var devils = new Score();
        devils.setTitle("Devil's Tower");

        assertThat(scoreRepository.findAll()).extracting(Score::getTitle).contains("Devil's Tower");
    }
}
