package br.manga.dto;

import java.util.List;

public record PedidoDTO(
    Long numeroPedido,
    String status,
    Double valorTotal,
    Long idUsuario,
    List<Long> idsMangas,
    Long idPagamento
) {}