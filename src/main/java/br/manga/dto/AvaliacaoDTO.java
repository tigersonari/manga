package br.manga.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoDTO(
    @Min(0) @Max(10) Double nota,
    @NotBlank String comentario,
    @NotNull Long mangaId
) {}