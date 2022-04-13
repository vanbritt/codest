package com.vanbritt.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorSlimDto {
    private Long id;
    @NotNull
    private String name;
}
