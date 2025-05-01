package br.manga.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminDTO(
    @NotBlank String nome,
    @NotBlank String email,
    @NotBlank String senhaHash,
    @NotBlank String endereco,
    @NotBlank String permissao 
) {}