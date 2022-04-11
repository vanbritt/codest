package com.vanbritt.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanbritt.backend.dto.EntryDto;
import com.vanbritt.backend.dto.EntryPostDto;
import com.vanbritt.backend.exception.ResourceNotFoundException;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Entry;
import com.vanbritt.backend.model.Sector;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static  org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EntryControllerTest {

    @InjectMocks
    private EntryController entryController;

    @Mock
    private EntryService entryService = new EntryServiceImpl();

    @Spy
    private MapStructMapper mapStructMapper = Mappers.getMapper(MapStructMapper.class);


    @Autowired
    private MockMvc mockMvc;

    private Entry entry1;
    private Entry entry2;
    private EntryPostDto entryDto;
    private List<Entry> entryList;
    private Sector sector;
    private Set<Sector> sectors;

    @BeforeEach
    public void setUp(){
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

        entryDto = EntryPostDto.builder()
                .name("Test Entry 2")
                .sectors(Arrays.asList(sector.getId()))
                .agreeToTerms(true)
                .build();

        entry2 = Entry.builder()
                .id(2L)
                .name(entryDto.getName())
                .agreeToTerms(entryDto.isAgreeToTerms())
                .build();

        entryList = new ArrayList<>();
        sectors = new HashSet<>();
        mockMvc= MockMvcBuilders.standaloneSetup(entryController).build();
    }

    @AfterEach
    public void tearDown(){
        sector=null;
        entryList=null;
        entry1=null;
        entry2=null;
    }

    @Test
    public void givenGetAllEntriesShouldReturnListOfEntries() throws Exception {
        entryList.add(entry1);
        entryList.add(entry2);
        when(entryService.getAllEntries()).thenReturn(entryList);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(entryList))
        ).andDo(MockMvcResultHandlers.print());
        verify(entryService, times(1)).getAllEntries();
    }

    @Test
    public void givenEntryPostDtoShouldReturnCreatedResponseStatus() throws Exception {

        // when
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/entries")
                        .contentType(MediaType.APPLICATION_JSON).content(
                        asJsonString(entryDto))
                ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void givenIdGetEntryByIdShouldThrowExceptionIfNotExists() throws Exception {
        Long id = 20L;
        // when getByEntryId is called non existing id , throw 404 Not found exception
        when(entryService.getEntryById(id)).thenThrow(new ResourceNotFoundException("Not Found"));
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/entries/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //verify the response status
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }


//use to convert objects to json string
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
