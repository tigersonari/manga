package br.manga.dto;

import java.time.LocalDate;

public record EditoraDTO(
    String nome,
    String sede,
    LocalDate fundacao,
    Integer idManga
    ) {}
