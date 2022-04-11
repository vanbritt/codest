package com.vanbritt.backend.repository;

import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.model.Sector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class EntryRepositoryTest {
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private SectorRepository sectorRepository;

    private Entry entry;
    private Sector sector;
    private Set<Sector> sectors;

    @BeforeEach
    void setUp(){
        sectors = new HashSet<>();

        sector = Sector.builder()
                .name("Parent")
                .build();

        entry = Entry.builder()
                .name("Entry Test")
                .agreeToTerms(true)
                .build();
    }

    @AfterEach
    void tearDown(){
        entryRepository.deleteAll();
        sector=null;
        entry=null;
    }

    @Test
    void givenGetEntriesShouldReturnListOfEntries(){
        sector = sectorRepository.save(sector);
        sectors.add(sector);
        entry.setSectors(sectors);

        entry = entryRepository.save(entry);

        List<Entry> entries = entryRepository.findAll();
        assertThat(entries).isNotNull();
        assertThat(entries.get(0).getName()).isEqualTo("Entry Test");
    }

}
