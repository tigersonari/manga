package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import br.manga.model.Pedido;
import br.manga.model.PedidoManga;

public record PedidoResponseDTO(
    Long id,
    Long numeroPedido,
    LocalDate data,
    String status,
    Double valorTotal,
    UsuarioResponseDTO usuario,
    List<ItemPedidoResponseDTO> itens
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if (pedido == null) return null;
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getNumeroPedido(),
            pedido.getData(),
            pedido.getStatus(),
            pedido.getValorTotal(),
            UsuarioResponseDTO.valueOf(pedido.getUsuario()),
            pedido.getMangasComprados().stream()
                .map(ItemPedidoResponseDTO::valueOf)
                .toList()
        );
    }
}

record ItemPedidoResponseDTO(
    Long mangaId,
    String mangaTitulo,
    Integer quantidade
) {
    public static ItemPedidoResponseDTO valueOf(PedidoManga pedidoManga) {
        if (pedidoManga == null) return null;
        return new ItemPedidoResponseDTO(
            pedidoManga.getManga(), 
            pedidoManga.getMangaEntity() != null ? pedidoManga.getMangaEntity().getTitulo() : null,
            pedidoManga.getQuantidade()
        );
    }
}