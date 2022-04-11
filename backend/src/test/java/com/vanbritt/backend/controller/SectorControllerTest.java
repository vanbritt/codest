package com.vanbritt.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanbritt.backend.mapstruct.mappers.MapStructMapper;
import com.vanbritt.backend.model.Sector;
import com.vanbritt.backend.service.Impl.SectorServiceImpl;
import com.vanbritt.backend.service.api.SectorService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SectorControllerTest {

    @InjectMocks
    private SectorController sectorController;

    @Mock
    private SectorService sectorService = new SectorServiceImpl();

    @Autowired
    private MockMvc mockMvc;

    @Spy
    private MapStructMapper mapStructMapper = Mappers.getMapper(MapStructMapper.class);

    private Sector sector;
    private List<Sector> sectorList;

    @BeforeEach
    public void setUp() {
        sector = Sector.builder()
                .id(1L)
                .name("Parent Sector")
                .build();
        sectorList = new ArrayList<>();
        mockMvc = MockMvcBuilders.standaloneSetup(sectorController).build();
    }

    @AfterEach
    public void tearDown() {
        sector = null;
        sectorList = null;
    }

    @Test
    void givenGetAllSectorsShouldReturnListOfSectors() throws Exception {
        sectorList.add(sector);
        when(sectorService.getAllSectors()).thenReturn(sectorList);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/sectors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sectorList))
        ).andDo(MockMvcResultHandlers.print());

        //check if method was called atleast once
        verify(sectorService).getAllSectors();
        verify(sectorService, times(1)).getAllSectors();


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
