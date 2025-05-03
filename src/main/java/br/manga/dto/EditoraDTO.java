package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record EditoraDTO(
    @NotBlank(message = "nome  vazio")
    String nome,
    
    @NotBlank
    String sede,
    
    
    LocalDate fundacao
) {}