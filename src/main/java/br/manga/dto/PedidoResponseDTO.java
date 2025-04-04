package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import br.manga.model.Manga;
import br.manga.model.Pedido;
import br.manga.model.Usuario;

public record PedidoResponseDTO(
    Long id,
    Long numeroPedido,
    LocalDate data,
    String status,
    List<Manga> mangasComprados,
    Double valorTotal,
    Usuario usuario
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if (pedido == null)
            return null;
        return new PedidoResponseDTO(
            pedido.getId(), pedido.getNumeroPedido(), pedido.getData(), 
            pedido.getStatus(), pedido.getMangasComprados(), pedido.getValorTotal(), pedido.getUsuario()
        );
    }
}
