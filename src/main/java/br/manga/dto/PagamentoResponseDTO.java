package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Pagamento;
import br.manga.model.Pedido;

public record PagamentoResponseDTO(
    String metodoPagamento,
    String status,
    LocalDate dataConfirmacao,
    Pedido pedido
) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        if (pagamento == null) return null;
        return new PagamentoResponseDTO(
            pagamento.getMetodoPagamento(),
            pagamento.getStatus(),
            pagamento.getDataConfirmacao(),
            pagamento.getPedido()
        );
    }
}