package com.kaibatech.gamecatalog.api.v1.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResource {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @Min(value = 1970)
    private Integer year;

    @NotBlank
    private String console;

    @NotNull
    private Boolean completed;

    @NotNull
    private Instant completion;

    private String notes;
    private String imageUrl;

}
