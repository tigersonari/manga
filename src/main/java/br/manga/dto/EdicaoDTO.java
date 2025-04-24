package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EdicaoDTO(
    @Positive Integer volume,
    @NotBlank String idioma,
    @NotNull LocalDate lancamento,
    @NotBlank String dimensao,
    @NotBlank String titulo,
    @NotNull Integer formatoId,
    @NotNull Integer tipoCapaId,
    @NotNull Integer statusId,
    Long mangaId
) {}