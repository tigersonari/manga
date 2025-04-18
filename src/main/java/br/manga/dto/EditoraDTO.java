package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record EditoraDTO(
    @NotBlank String nome,
    @NotBlank String sede,
    LocalDate fundacao
) {}