package com.vanbritt.backend.repository;

import com.vanbritt.backend.model.Sector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class SectorRepositoryTest {

    @Autowired
    private SectorRepository sectorRepository;
    private Sector parentSector;
    private Sector childSector;

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown(){
        sectorRepository.deleteAll();
        parentSector=null;
        childSector=null;
    }

    @Test
    void givenGetSectorsShouldReturnListOfSectors(){
        parentSector = Sector.builder()
                .name("Parent")
                .build();

        parentSector = sectorRepository.save(parentSector);

        childSector = Sector.builder()
                .name("Child sector")
                .parentSector(parentSector)
                .build();
        childSector = sectorRepository.save(childSector);

        List<Sector> sectors = sectorRepository.findAll();

        assertThat(sectors.size()).isEqualTo(2);
    }
}
