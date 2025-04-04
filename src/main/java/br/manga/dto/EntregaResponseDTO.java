package br.manga.dto;

import br.manga.model.Entrega;
import br.manga.model.Pedido;

 public record EntregaResponseDTO(
    Long id,
    String endereco,
    String codigoRastreio,
    String status,
    Pedido pedido
) {
    public static EntregaResponseDTO valueOf(Entrega entrega) {
        if (entrega == null)
            return null;
        return new EntregaResponseDTO(
            entrega.getId(), entrega.getEndereco(), entrega.getCodigoRastreio(),
            entrega.getStatus(), entrega.getPedido()
        );
    }
}