package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Pagamento;


public record PagamentoResponseDTO(
    Long id,
    String metodoPagamento,
    String status,
    LocalDate dataConfirmacao,
    Long idPedido
) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        if (pagamento == null)
            return null;
        return new PagamentoResponseDTO(
            pagamento.getId(), pagamento.getMetodoPagamento(), pagamento.getStatus(),
            pagamento.getDataConfirmacao(), pagamento.getIdPedido()
        );
    }
}

