package com.vanbritt.backend.integration;

import com.vanbritt.backend.controller.SectorController;
import com.vanbritt.backend.dto.SectorDto;
import com.vanbritt.backend.model.Sector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SectorControllerTest {

    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void givenGetAllSectorsShouldReturnListofSectors(){
        ResponseEntity<SectorDto[]> sectorList = testRestTemplate
                .getForEntity("http://localhost:"+port+"/api/v1/sectors",SectorDto[].class);
        assertThat(sectorList.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
