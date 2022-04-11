package com.vanbritt.backend.repository;

import com.vanbritt.backend.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long>{

}
