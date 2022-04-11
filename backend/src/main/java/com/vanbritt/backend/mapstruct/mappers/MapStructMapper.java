package com.vanbritt.backend.mapstruct.mappers;

import com.vanbritt.backend.dto.*;
import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.model.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = "spring"
)
@Component
public interface MapStructMapper {
    SectorDto sectorToSectorDto(Sector sector);

    SectorSlimDto sectorToSectorSlimDto(Sector sector);

    EntryGetDto entryToEntryGetDto(Entry entry);

    @Mapping(target = "sectors", ignore = true)
    Entry entryPostDtoToEntry(EntryPostDto entryPostDto);

    List<SectorDto> sectorsToSectorDto(List<Sector> sectors);

    List<SectorSlimDto> sectorsToSectorSlimDto(List<Sector> sectors);

    Set<SectorDto> sectorsToSectorDto(Set<Sector> sectors);

    List<EntryGetDto> entriesToEntryGetDto(List<Entry> entries);
}