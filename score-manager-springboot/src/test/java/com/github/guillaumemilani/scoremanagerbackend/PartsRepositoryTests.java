package com.github.guillaumemilani.scoremanagerbackend;

import com.github.guillaumemilani.scoremanagerbackend.model.*;
import com.github.guillaumemilani.scoremanagerbackend.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static com.github.guillaumemilani.scoremanagerbackend.repository.PartSpecs.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.jpa.domain.Specification.allOf;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PartsRepositoryTests {
    @Autowired
    private PartsRepository partsRepository;

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    @Transactional
    void testFindAll() {
        assertThat(partsRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    void testFindByPlayerAndSeason() {
        Instrument trombone = new Instrument();
        trombone.setName("Trombone");
        instrumentRepository.save(trombone);
        Instrument altoSax = new Instrument();
        altoSax.setName("Saxophone alto");
        instrumentRepository.save(altoSax);
        Instrument bassoon = new Instrument();
        bassoon.setName("Basson");
        instrumentRepository.save(bassoon);
        Instrument trumpet = new Instrument();
        trumpet.setName("Trompette");
        instrumentRepository.save(trumpet);

        Player gMilani = new Player();
        gMilani.setFirstName("Guillaume");
        gMilani.setLastName("Milani");
        gMilani.getPlayedInstruments().add(trombone);
        playerRepository.save(gMilani);

        Player gHarris = new Player();
        gHarris.setFirstName("Guillaume");
        gHarris.setLastName("Harris");
        gHarris.getPlayedInstruments().add(altoSax);
        gHarris.getPlayedInstruments().add(bassoon);
        playerRepository.save(gHarris);

        Player jBrahier = new Player();
        jBrahier.setFirstName("Julien");
        jBrahier.setLastName("Brahier");
        jBrahier.getPlayedInstruments().add(trumpet);
        playerRepository.save(jBrahier);

        Player rZbinden = new Player();
        rZbinden.setFirstName("Renaud");
        rZbinden.setLastName("Zbinden");
        rZbinden.getPlayedInstruments().add(altoSax);
        playerRepository.save(rZbinden);

        Season spring24 = new Season();
        spring24.setName("2024-1");
        seasonRepository.save(spring24);

        Score devilsTower = new Score();
        devilsTower.setTitle("Devil's Tower");
        devilsTower.setComposer("Thomas Doss");
        scoreRepository.save(devilsTower);

        Score lesGendarmes = new Score();
        lesGendarmes.setTitle("Les Gendarmes de Saint-Tropez");
        lesGendarmes.setComposer("Raymond Lefebvre");
        scoreRepository.save(lesGendarmes);

        Part devilsBassoon1 = new Part();
        devilsBassoon1.setInstrument(bassoon);
        devilsBassoon1.setScore(devilsTower);
        devilsBassoon1.setVoice(1);
        partsRepository.save(devilsBassoon1);

        Part devilsTrombone1 = new Part();
        devilsTrombone1.setInstrument(trombone);
        devilsTrombone1.setScore(devilsTower);
        devilsTrombone1.setVoice(1);
        partsRepository.save(devilsTrombone1);

        Part devilsTrombone2 = new Part();
        devilsTrombone2.setInstrument(trombone);
        devilsTrombone2.setScore(devilsTower);
        devilsTrombone2.setVoice(2);
        partsRepository.save(devilsTrombone2);

        Part devilsAltoSax1 = new Part();
        devilsAltoSax1.setInstrument(altoSax);
        devilsAltoSax1.setScore(devilsTower);
        devilsAltoSax1.setVoice(1);
        partsRepository.save(devilsAltoSax1);

        Part gendarmesAltoSax1 = new Part();
        gendarmesAltoSax1.setInstrument(altoSax);
        gendarmesAltoSax1.setScore(lesGendarmes);
        gendarmesAltoSax1.setVoice(1);
        partsRepository.save(gendarmesAltoSax1);

        Play ghDevils = new Play();
        ghDevils.setPlayer(gHarris);
        ghDevils.setSeason(spring24);
        ghDevils.setPart(devilsBassoon1);
        playRepository.save(ghDevils);

        Play gmDevils = new Play();
        gmDevils.setPlayer(gMilani);
        gmDevils.setSeason(spring24);
        gmDevils.setPart(devilsTrombone1);
        playRepository.save(gmDevils);

        Play ghGendarmes = new Play();
        ghGendarmes.setPlayer(gHarris);
        ghGendarmes.setSeason(spring24);
        ghGendarmes.setPart(gendarmesAltoSax1);
        playRepository.save(ghGendarmes);

        Play rzGendarmes = new Play();
        rzGendarmes.setPlayer(rZbinden);
        rzGendarmes.setSeason(spring24);
        rzGendarmes.setPart(gendarmesAltoSax1);
        playRepository.save(rzGendarmes);

        assertThat(partsRepository.findAll(allOf(hasPlayer(gHarris), hasSeason(spring24))))
                .containsExactly(devilsBassoon1, gendarmesAltoSax1);

        assertThat(partsRepository.findAll(allOf(hasPlayer(gMilani), hasSeason(spring24))))
                .containsExactly(devilsTrombone1);

        assertThat(partsRepository.findAll(allOf(hasPlayer(jBrahier), hasSeason(spring24))))
                .isEmpty();

        assertThat(partsRepository.findAll(allOf(hasPlayer(rZbinden), hasSeason(spring24))))
                .containsExactly(gendarmesAltoSax1);
    }

    @Test
    @Transactional
    void testFindByScoreAndSeason() {
        Instrument trombone = new Instrument();
        trombone.setName("Trombone");
        instrumentRepository.save(trombone);
        Instrument altoSax = new Instrument();
        altoSax.setName("Saxophone alto");
        instrumentRepository.save(altoSax);
        Instrument bassoon = new Instrument();
        bassoon.setName("Basson");
        instrumentRepository.save(bassoon);
        Instrument trumpet = new Instrument();
        trumpet.setName("Trompette");
        instrumentRepository.save(trumpet);

        Player gMilani = new Player();
        gMilani.setFirstName("Guillaume");
        gMilani.setLastName("Milani");
        gMilani.getPlayedInstruments().add(trombone);
        playerRepository.save(gMilani);

        Player gHarris = new Player();
        gHarris.setFirstName("Guillaume");
        gHarris.setLastName("Harris");
        gHarris.getPlayedInstruments().add(altoSax);
        gHarris.getPlayedInstruments().add(bassoon);
        playerRepository.save(gHarris);

        Player jBrahier = new Player();
        jBrahier.setFirstName("Julien");
        jBrahier.setLastName("Brahier");
        jBrahier.getPlayedInstruments().add(trumpet);
        playerRepository.save(jBrahier);

        Player rZbinden = new Player();
        rZbinden.setFirstName("Renaud");
        rZbinden.setLastName("Zbinden");
        rZbinden.getPlayedInstruments().add(altoSax);
        playerRepository.save(rZbinden);

        Season spring24 = new Season();
        spring24.setName("2024-1");
        seasonRepository.save(spring24);

        Season automn24 = new Season();
        automn24.setName("2024-2");
        seasonRepository.save(automn24);

        Score devilsTower = new Score();
        devilsTower.setTitle("Devil's Tower");
        devilsTower.setComposer("Thomas Doss");
        scoreRepository.save(devilsTower);

        Score lesGendarmes = new Score();
        lesGendarmes.setTitle("Les Gendarmes de Saint-Tropez");
        lesGendarmes.setComposer("Raymond Lefebvre");
        scoreRepository.save(lesGendarmes);

        Part devilsBassoon1 = new Part();
        devilsBassoon1.setInstrument(bassoon);
        devilsBassoon1.setScore(devilsTower);
        devilsBassoon1.setVoice(1);
        partsRepository.save(devilsBassoon1);

        Part devilsTrombone1 = new Part();
        devilsTrombone1.setInstrument(trombone);
        devilsTrombone1.setScore(devilsTower);
        devilsTrombone1.setVoice(1);
        partsRepository.save(devilsTrombone1);

        Part devilsTrombone2 = new Part();
        devilsTrombone2.setInstrument(trombone);
        devilsTrombone2.setScore(devilsTower);
        devilsTrombone2.setVoice(2);
        partsRepository.save(devilsTrombone2);

        Part devilsAltoSax1 = new Part();
        devilsAltoSax1.setInstrument(altoSax);
        devilsAltoSax1.setScore(devilsTower);
        devilsAltoSax1.setVoice(1);
        partsRepository.save(devilsAltoSax1);

        Part gendarmesAltoSax1 = new Part();
        gendarmesAltoSax1.setInstrument(altoSax);
        gendarmesAltoSax1.setScore(lesGendarmes);
        gendarmesAltoSax1.setVoice(1);
        partsRepository.save(gendarmesAltoSax1);

        Play ghDevils = new Play();
        ghDevils.setPlayer(gHarris);
        ghDevils.setSeason(spring24);
        ghDevils.setPart(devilsBassoon1);
        playRepository.save(ghDevils);

        Play gmDevils = new Play();
        gmDevils.setPlayer(gMilani);
        gmDevils.setSeason(spring24);
        gmDevils.setPart(devilsTrombone1);
        playRepository.save(gmDevils);

        Play ghGendarmes = new Play();
        ghGendarmes.setPlayer(gHarris);
        ghGendarmes.setSeason(spring24);
        ghGendarmes.setPart(gendarmesAltoSax1);
        playRepository.save(ghGendarmes);

        Play rzDevils = new Play();
        rzDevils.setPlayer(rZbinden);
        rzDevils.setSeason(spring24);
        rzDevils.setPart(devilsAltoSax1);
        playRepository.save(rzDevils);

        Play rzGendarmes = new Play();
        rzGendarmes.setPlayer(rZbinden);
        rzGendarmes.setSeason(spring24);
        rzGendarmes.setPart(gendarmesAltoSax1);
        playRepository.save(rzGendarmes);

        assertThat(partsRepository.findAll(allOf(hasScore(devilsTower), hasSeason(spring24))))
                .containsExactly(devilsBassoon1, devilsTrombone1, devilsAltoSax1);

        assertThat(partsRepository.findAll(allOf(hasScore(lesGendarmes), hasSeason(spring24))))
                .containsExactly(gendarmesAltoSax1);

        assertThat(partsRepository.findAll(allOf(hasScore(devilsTower), hasSeason(automn24))))
                .isEmpty();
    }
}
