package br.manga.dto;

import java.time.LocalDate;

public record EdicaoDTO(
    Integer volume,
    String idioma,
    LocalDate lancamento,
    String dimensao,
    Integer idFormato,
    Integer idStatus,
    Integer idTipoCapa,
    Integer idManga
    ) {}
