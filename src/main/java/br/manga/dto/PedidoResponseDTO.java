
package br.manga.dto;

import java.util.List;

import br.manga.model.Pagamento;
import br.manga.model.Pedido;
import br.manga.model.PedidoManga;

public record PedidoResponseDTO(
    Long numeroPedido,
    String status,
    Double valorTotal,
    String usuario,
    List<String> mangas,
    Pagamento pagamento
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if (pedido == null) return null;
        return new PedidoResponseDTO(
            pedido.getNumeroPedido(),
            pedido.getStatus(),
            pedido.getValorTotal(),
            pedido.getUsuario() != null ? pedido.getUsuario().getNome() : null,
            pedido.getMangasComprados() != null 
                ? pedido.getMangasComprados().stream()
                    .map(PedidoManga::getMangaEntity)
                    .map(manga -> manga != null ? manga.getTitulo() : null)
                    .toList() 
                : null,
            pedido.getPagamento()
        );
    }
}