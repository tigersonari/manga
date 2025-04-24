package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Edicao;

public record EdicaoResponseDTO(
    Integer volume,
    String idioma,
    LocalDate lancamento,
    String dimensao,
    String titulo,
    String formato,
    String tipoCapa,
    String status,
    String manga
) {
    public static EdicaoResponseDTO valueOf(Edicao edicao) {
        if (edicao == null) return null;
        return new EdicaoResponseDTO(
            edicao.getVolume(),
            edicao.getIdioma(),
            edicao.getLancamento(),
            edicao.getDimensao(),
            edicao.getTitulo(),
            edicao.getFormato() != null ? edicao.getFormato().name() : null,
            edicao.getTipoCapa() != null ? edicao.getTipoCapa().name() : null,
            edicao.getStatus() != null ? edicao.getStatus().name() : null,
            edicao.getManga() != null ? edicao.getManga().getTitulo() : null
        );
    }
}