package com.vanbritt.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SectorSlimDto {
    private Long id;
    @NotNull
    private String name;
}
