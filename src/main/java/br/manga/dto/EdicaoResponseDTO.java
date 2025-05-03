package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Edicao;
import br.manga.model.Formato;
import br.manga.model.Manga;
import br.manga.model.Status;
import br.manga.model.TipoCapa;

public record EdicaoResponseDTO(
    Long id,
    Integer volume,
    String idioma,
    LocalDate lancamento,
    String dimensao,
    String titulo,
    Formato formato,
    TipoCapa tipoCapa,
    Status status,
    Manga manga
) {
    public static EdicaoResponseDTO valueOf(Edicao edicao) {
        if (edicao == null) return null;
        return new EdicaoResponseDTO(
            edicao.getId(),
            edicao.getVolume(),
            edicao.getIdioma(),
            edicao.getLancamento(),
            edicao.getDimensao(),
            edicao.getTitulo(),
            edicao.getFormato(),
            edicao.getTipoCapa(),
            edicao.getStatus(),
            edicao.getManga()
        );
    }
}