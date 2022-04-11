package com.vanbritt.backend.service.Impl;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Sector;
import com.vanbritt.backend.repository.SectorRepository;
import com.vanbritt.backend.service.api.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private MapStructMapper mapStructMapper;


    @Override
    public List<Sector> getAllSectors() {
        return sectorRepository.findAllByParentSectorNull();
    }

}
