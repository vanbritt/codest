package com.vanbritt.backend.repository;

import com.vanbritt.backend.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByParentSectorNull();
}
