package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EdicaoDTO(
    @NotNull @Positive Integer volume,
    @NotBlank String idioma,
    @NotNull LocalDate lancamento,
    @NotBlank String dimensao,
    @NotBlank @Size(max = 100) String titulo,
    @NotNull Integer formatoId,
    @NotNull Integer tipoCapaId,
    @NotNull Integer statusId,
    @NotNull Long mangaId
) {}