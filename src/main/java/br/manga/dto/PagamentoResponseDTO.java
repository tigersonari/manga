package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Pagamento;

public record PagamentoResponseDTO(
    String metodoPagamento,
    String status,
    LocalDate dataConfirmacao,
    Long idPedido
) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        if (pagamento == null) return null;
        return new PagamentoResponseDTO(
            pagamento.getMetodoPagamento(),
            pagamento.getStatus(),
            pagamento.getDataConfirmacao(),
            pagamento.getPedido() != null ? pagamento.getPedido().getId() : null
        );
    }
}