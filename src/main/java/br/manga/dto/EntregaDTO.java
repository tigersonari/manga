package br.manga.dto;

 public record EntregaDTO(
    Long id,
    String endereco,
    String codigoRastreio,
    String status,
    Integer idPedido
) {
}