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
        Optional<Entry> entry = entryRepository.findById(id);
        if (!entry.isPresent())
            throw new ResourceNotFoundException("Entry not Found");
        return entry.get();
    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Entry saveEntry(EntryPostDto entryPostDto) {
        Entry entry =null;

        if(entryPostDto.getId() != null){
            Optional<Entry> optionalEntry =  entryRepository.findById(entryPostDto.getId());
            if(!optionalEntry.isPresent())
                throw new ResourceNotFoundException("Entry Not Found");
            entry = optionalEntry.get();
            entry.setName(entryPostDto.getName());
            entry.setAgreeToTerms(entryPostDto.isAgreeToTerms());
        }else {
            entry = mapStructMapper.entryPostDtoToEntry(entryPostDto);
        }
        Set<Sector> sectors = entryPostDto.getSectors().stream().map(id -> {
            Optional<Sector> sector = sectorRepository.findById(id);
            if (!sector.isPresent())
                throw new ResourceNotFoundException("Sector not found exception");
            return sector.get();
        }).collect(Collectors.toSet());

        entry.setSectors(sectors);

        return entryRepository.save(entry);
    }

}
