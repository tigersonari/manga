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
    Status status,
    Formato Formato,
    TipoCapa tipoCapa,
    Manga manga
) {
    
        public static EdicaoResponseDTO valueOf(Edicao edicao) {
            if (edicao == null)
                return null;
            return new EdicaoResponseDTO(edicao.getId(), edicao.getVolume(), edicao.getIdioma(), edicao.getLancamento(), edicao.getDimensao(),
            edicao.getStatus(), edicao.getFormato(), edicao.getTipoCapa(), edicao.getManga());
    }
    
}
