package com.vanbritt.backend.service;

import com.vanbritt.backend.dto.EntryDto;
import com.vanbritt.backend.dto.EntryGetDto;
import com.vanbritt.backend.dto.EntryPostDto;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.model.Sector;
import com.vanbritt.backend.repository.EntryRepository;
import com.vanbritt.backend.repository.SectorRepository;
import com.vanbritt.backend.service.Impl.EntryServiceImpl;
import com.vanbritt.backend.service.api.EntryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EntryServiceTest {
    @Spy
    @InjectMocks
    private EntryService entryService = new EntryServiceImpl();

    @Mock
    private EntryRepository entryRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Spy
    private MapStructMapper mapStructMapper = Mappers.getMapper(MapStructMapper.class);

    private Entry entry1;
    private Entry entry2;
    private EntryDto entryDto;
    private EntryPostDto entryPostDto;
    private List<Entry> entryList;
    private Sector sector;
    private Set<Sector> sectors;

    @BeforeEach
    public void setUp() {
        sector = Sector.builder()
                .id(1L)
                .name("Parent Sector")
                .build();
        entry1 = Entry.builder()
                .id(1L)
                .name("Test Entry 1")
                .agreeToTerms(true)
                .sectors(new HashSet<>())
                .build();
        entry2 = Entry.builder()
                .id(2L)
                .name("Test Entry 2")
                .agreeToTerms(true)
                .sectors(new HashSet<>())
                .build();
        entryPostDto = EntryPostDto.builder()
                .sectors(Arrays.asList(sector.getId()))
                .agreeToTerms(true)
                .build();
        entryList = new ArrayList<>();
        sectors = new HashSet<>();
        sectors.add(sector);
        entry1.setSectors(sectors);
        entry2.setSectors(sectors);
    }

    @AfterEach
    public void tearDown() {
        sector = null;
        entryList = null;
        entry1 = null;
        entry2 = null;
    }

    @Test
    public void givenGetAllEntriesShouldReturnListOfEntries() {
        entryList.add(entry1);
        entryList.add(entry2);

        when(entryRepository.findAll()).thenReturn(entryList);

        List<Entry> expectedEntryList = entryService.getAllEntries();

        //testing if expected dto conversion list equals actual conversion list
        assertThat(expectedEntryList).isEqualTo(entryList);
        verify(entryRepository, times(1)).findAll();

    }

    @Test
    public void givenIdThenShouldReturnEntryOfThatId() {


        when(entryRepository.findById(entry1.getId())).thenReturn(Optional.of(entry1));

        Entry expectedEntry = entryService.getEntryById(entry1.getId());
        assertThat(entry1).isNotNull();
        assertThat(entry1).isEqualTo(expectedEntry);
        verify(entryRepository, times(1)).findById(entry1.getId());
    }

    @Test
    public void givenEntryToAddShouldReturnAddedEntry() {


        when(entryRepository.save(any(Entry.class))).thenReturn(entry1);
        when(sectorRepository.findById(sector.getId())).thenReturn(Optional.of(sector));

        Entry savedEntry = entryService.saveEntry(entryPostDto);
        assertThat(savedEntry).isNotNull();
        assertThat(savedEntry).isEqualTo(entry1);
        verify(entryRepository, times(1)).save(any());
    }

    @Test
    public void givenEntryToUpdateShouldReturnUpdatedEntry() {
        //setting the id of the Post dto data
        entryPostDto.setId(entry1.getId());
        //needed to find if entry exist in database
        when(entryRepository.findById(entry1.getId())).thenReturn(Optional.of(entry1));
        when(sectorRepository.findById(sector.getId())).thenReturn(Optional.of(sector));
        when(entryRepository.save(any(Entry.class))).thenReturn(entry1);

        Entry savedEntry = entryService.saveEntry(entryPostDto);
        assertThat(savedEntry).isNotNull();
        assertThat(savedEntry).isEqualTo(entry1);
        verify(entryRepository, times(1)).save(any());
    }

}
