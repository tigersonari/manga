package br.manga.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorDTO(
    @NotBlank String nome,
    @NotBlank String nacionalidade
) {}