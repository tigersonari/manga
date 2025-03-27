package br.manga.dto;

public record AvaliacaoDTO(
    double nota,
    String comentario,
    Integer idManga
    ) {}
