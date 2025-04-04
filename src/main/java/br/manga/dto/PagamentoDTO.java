package br.manga.dto;

import java.time.LocalDate;


public record PagamentoDTO(
    Long id,
    String metodoPagamento,
    String status,
    LocalDate dataConfirmacao,
    Integer idPedido
) {
}

