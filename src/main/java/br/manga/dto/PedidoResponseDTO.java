package br.manga.dto;

import java.util.List;

import br.manga.model.Pedido;

public record PedidoResponseDTO(
    Long id,
    Long numeroPedido,
    String status,
    Double valorTotal,
    String usuario,
    List<String> mangas,
    String pagamento
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if (pedido == null) return null;
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getNumeroPedido(),
            pedido.getStatus(),
            pedido.getValorTotal(),
            pedido.getUsuario() != null ? pedido.getUsuario().getNome() : null,
            pedido.getMangasComprados() != null ? pedido.getMangasComprados().stream().map(m -> m.getTitulo()).toList() : null,
            pedido.getPagamento() != null ? pedido.getPagamento().getMetodoPagamento() : null
        );
    }
}