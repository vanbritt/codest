package com.vanbritt.backend.service.Impl;

import com.vanbritt.backend.dto.EntryPostDto;
import com.vanbritt.backend.exception.ResourceNotFoundException;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.model.Sector;
import com.vanbritt.backend.repository.EntryRepository;
import com.vanbritt.backend.repository.SectorRepository;
import com.vanbritt.backend.service.api.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EntryServiceImpl implements EntryService {
    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public Entry getEntryById(Long id) {
        return entryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entry not Found with id: " + id));
    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Entry saveEntry(EntryPostDto entryPostDto) {
        Entry entry = null;

        if (entryPostDto.getId() != null) {
            //Checking If the provided entry with id exist in the database
            entry = entryRepository.findById(entryPostDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Entry Not Found with id: " + entryPostDto.getId()));

            entry.setName(entryPostDto.getName());
            entry.setAgreeToTerms(entryPostDto.isAgreeToTerms());

        } else {
            entry = mapStructMapper.entryPostDtoToEntry(entryPostDto);
        }
        //Verifying if every supplied sectors exist in the database
        Set<Sector> sectors = entryPostDto.getSectors()
                .stream()
                .map(id -> sectorRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Sector not found with id: " + id)))
                .collect(Collectors.toSet());

        entry.setSectors(sectors);
        return entryRepository.save(entry);
    }

}
