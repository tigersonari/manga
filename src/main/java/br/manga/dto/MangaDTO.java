package br.manga.dto;

import java.time.LocalDate;

public record MangaDTO(
    String titulo,
    String isbn,
    LocalDate lancamento,
    String sinopse,
    Integer idEstoque,
    Integer idGenero,
    Integer idClassificacao,
    Integer idEdicao,
    Integer idEditora,
    Integer idAutor,
    Integer idAvaliacao
) {}
