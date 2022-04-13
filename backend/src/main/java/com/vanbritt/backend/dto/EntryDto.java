package com.vanbritt.backend.dto;


import com.vanbritt.backend.model.Sector;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<Sector> sectors;

    @NotNull
    private boolean agreeToTerms;

}
