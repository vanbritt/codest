package com.vanbritt.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EntryPostDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<Long> sectors;

    @NotNull
    private boolean agreeToTerms;
}
