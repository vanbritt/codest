package com.vanbritt.backend.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vanbritt.backend.model.Sector;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SectorDto {
    private Long id;

    //private SectorSlimDto parentSector;

    @NotNull
    private String name;

    private Set<SectorDto> subSectors;
}
