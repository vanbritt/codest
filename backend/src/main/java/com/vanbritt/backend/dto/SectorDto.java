package com.vanbritt.backend.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vanbritt.backend.model.Sector;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorDto {
    private Long id;

    @NotNull
    private String name;

    private Set<SectorDto> subSectors;
}
