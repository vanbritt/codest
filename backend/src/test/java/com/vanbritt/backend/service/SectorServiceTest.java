package com.vanbritt.backend.service;

import com.vanbritt.backend.dto.SectorDto;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Sector;
import com.vanbritt.backend.repository.SectorRepository;
import com.vanbritt.backend.service.Impl.SectorServiceImpl;
import com.vanbritt.backend.service.api.SectorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SectorServiceTest {

    @Mock
    private SectorRepository sectorRepository;

    @Spy //use to mock the service partially
    @InjectMocks
    private SectorService sectorService = new SectorServiceImpl();
    private Sector parentSector;
    private Sector childSector;
    private List<Sector> sectors;


    @BeforeEach
    public void setUp() {
        parentSector = Sector.builder()
                .id(1L)
                .name("Parent Sector")
                .build();

        childSector = Sector.builder()
                .id(2L)
                .parentSector(parentSector)
                .name("Child Sector")
                .build();
        sectors = new ArrayList<>();
        sectors.add(parentSector);
        sectors.add(childSector);
    }

    @AfterEach
    public void tearDown() {
        parentSector = childSector = null;
        sectors = null;
    }

    @Test
    void givenGetAllSectorsShouldReturnAllSectors() {
        //stubbing repository to return specific sectors list  when called
        lenient().when(sectorRepository.findAll()).thenReturn(sectors);
        List<Sector> sectorsList = sectorService.getAllSectors();
        assertThat(sectorsList).isEqualTo(sectorsList);
    }

    @Test
    void givenGetAllSectorsShouldReturnParentSectorsWithChildSectors() {
        List<Sector> parentSectorList = new ArrayList<>();
        parentSector.addSubSector(childSector);
        parentSectorList.add(parentSector);
        //stubbing repository to return specific sectors list  when called
        when(sectorRepository.findAllByParentSectorNull()).thenReturn(parentSectorList);
        List<Sector> sectorsList = sectorService.getAllSectors();

        assertThat(sectorsList).isEqualTo(sectorsList);
        verify(sectorRepository, times(1)).findAllByParentSectorNull();
    }

}
