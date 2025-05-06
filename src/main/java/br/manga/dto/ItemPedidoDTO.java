package br.manga.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemPedidoDTO(
    @NotNull(message = "Mangá não pode ser vazio")
    Long mangaId,
    
    @NotNull(message = "Quantidade não pode ser nula")
    @Positive(message = "Quantidade deve ser maior que zero")
    Integer quantidade
) {}