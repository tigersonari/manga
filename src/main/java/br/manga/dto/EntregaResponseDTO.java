package br.manga.dto;

import br.manga.model.Entrega;

public record EntregaResponseDTO(
    String endereco,
    String codigoRastreio,
    String status,
    Long pedidoId
) {
    public static EntregaResponseDTO valueOf(Entrega entrega) {
        if (entrega == null) return null;
        return new EntregaResponseDTO(
            entrega.getEndereco(),
            entrega.getCodigoRastreio(),
            entrega.getStatus(),
            entrega.getPedido() != null ? entrega.getPedido().getId() : null
        );
    }
}