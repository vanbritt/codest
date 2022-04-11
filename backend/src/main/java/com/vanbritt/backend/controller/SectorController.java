package com.vanbritt.backend.controller;

import com.vanbritt.backend.dto.SectorDto;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.model.Sector;
import com.vanbritt.backend.repository.EntryRepository;
import com.vanbritt.backend.repository.SectorRepository;
import com.vanbritt.backend.service.api.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SectorController {
    @Autowired
    private SectorService sectorService;
    @Autowired
    private  SectorRepository sectorRepository;
    @Autowired
    private MapStructMapper mapStructMapper;

    @GetMapping("/sectors")
    public ResponseEntity<List<SectorDto>> getSectors(){
        return new ResponseEntity<List<SectorDto>>(mapStructMapper.sectorsToSectorDto(sectorService.getAllSectors()), HttpStatus.OK);
    }

}
