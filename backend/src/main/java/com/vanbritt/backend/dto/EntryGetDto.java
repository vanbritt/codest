package com.vanbritt.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EntryGetDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<SectorSlimDto> sectors;

    @NotNull
    private boolean agreeToTerms;

}

