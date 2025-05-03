
package br.manga.dto;

import java.util.List;

import br.manga.model.Pedido;
import br.manga.model.PedidoManga;
import br.manga.model.Usuario;

public record PedidoResponseDTO(
    Long numeroPedido,
    String status,
    Double valorTotal,
    Usuario usuario,
    List<String> mangas
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if (pedido == null) return null;
        return new PedidoResponseDTO(
            pedido.getNumeroPedido(),
            pedido.getStatus(),
            pedido.getValorTotal(),
            pedido.getUsuario(),
            pedido.getMangasComprados() != null 
                ? pedido.getMangasComprados().stream()
                    .map(PedidoManga::getMangaEntity)
                    .map(manga -> manga != null ? manga.getTitulo() : null)
                    .toList() 
                : null
        );
    }
}