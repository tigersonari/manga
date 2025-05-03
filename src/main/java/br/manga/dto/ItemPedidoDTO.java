package br.manga.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTO(
    @NotNull(message = "Mangá não pode ser vazio")
    Long mangaId,
    
    @NotNull(message = "Quantidade não pode ser nula")
    @Positive(message = "Quantidade deve ser maior que zero")
    Integer quantidade
) {}