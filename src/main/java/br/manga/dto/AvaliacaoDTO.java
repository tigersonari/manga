package br.manga.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoDTO(
    @NotNull(message = "nota não pode ser nula")
    @Min(value = 0, message = "nota mínima é 0")
    @Max(value = 10, message = "nota máxima é 10")
    Double nota,
    
    @NotBlank(message = "insira um comentário")
    String comentario,
    
    @NotNull(message = "mangá é obbrigatório")
    Long mangaId
) {}