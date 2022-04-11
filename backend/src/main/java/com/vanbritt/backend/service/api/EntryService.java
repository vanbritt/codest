package com.vanbritt.backend.service.api;

import com.vanbritt.backend.dto.EntryGetDto;
import com.vanbritt.backend.dto.EntryPostDto;
import com.vanbritt.backend.model.Entry;

import java.util.List;

public interface EntryService {

    Entry getEntryById(Long id);

    List<Entry> getAllEntries();

    Entry saveEntry(EntryPostDto entryPostDto);

}
