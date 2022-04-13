package com.vanbritt.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryPostDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<Long> sectors;

    @NotNull
    private boolean agreeToTerms;
}
