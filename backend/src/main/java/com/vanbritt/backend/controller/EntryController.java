package com.vanbritt.backend.controller;

import com.vanbritt.backend.dto.*;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.service.api.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EntryController {
    @Autowired
    private EntryService entryService;

    @Autowired
    private MapStructMapper mapStructMapper;

    @GetMapping("/entries")
    public ResponseEntity<List<EntryGetDto>> getAllEntries() {
        return new ResponseEntity<List<EntryGetDto>>(mapStructMapper.entriesToEntryGetDto(entryService.getAllEntries()), HttpStatus.OK);
    }

    @PostMapping("/entries")
    public ResponseEntity<EntryGetDto> addEntry(@RequestBody @Valid EntryPostDto entryPostDto) {
        EntryGetDto savedEntry = mapStructMapper.entryToEntryGetDto(entryService.saveEntry(entryPostDto));
        return new ResponseEntity<EntryGetDto>(savedEntry, HttpStatus.CREATED);
    }


    @PutMapping("/entries")
    public ResponseEntity<EntryGetDto> updateEntry(@RequestBody @Valid EntryPostDto entryPostDto) {
        EntryGetDto updatedEntry = mapStructMapper.entryToEntryGetDto(entryService.saveEntry(entryPostDto));

        return new ResponseEntity<EntryGetDto>(updatedEntry, HttpStatus.OK);
    }

    @GetMapping("/entries/{id}")
    public EntityModel<EntryGetDto> getEntryById(@PathVariable("id") long id) {

        EntryGetDto entryGetDto = mapStructMapper.entryToEntryGetDto(entryService.getEntryById(id));
        EntityModel entityModel = EntityModel.of(entryGetDto);

        //generating the link to all entries while referencing the method
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllEntries());

        //creating the link to all entries
        entityModel.add(linkBuilder.withRel("all-entries"));

        return entityModel;
    }

    @GetMapping("/entries/{id}/sectors")
    public ResponseEntity<List<SectorSlimDto>> getEntrySectors(@PathVariable long id) {
        EntryGetDto entry = mapStructMapper.entryToEntryGetDto(entryService.getEntryById(id));

        return new ResponseEntity<>(entry.getSectors(), HttpStatus.OK);
    }

}
