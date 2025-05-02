package br.manga.dto;

import jakarta.validation.constraints.NotBlank;

public record EntregaDTO(
    @NotBlank String endereco,
    @NotBlank String codigoRastreio,
    String status,
    Long idPedido
) {}