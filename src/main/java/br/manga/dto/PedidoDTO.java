package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import br.manga.model.Manga;

public record PedidoDTO(
    Long id,
    Long numeroPedido,
    LocalDate data,
    String status,
    List<Manga> mangasComprados,
    Double valorTotal,
    Integer idUsuario
) {}
