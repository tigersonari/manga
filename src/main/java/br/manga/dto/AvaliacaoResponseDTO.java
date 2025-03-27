package br.manga.dto;

import br.manga.model.Avaliacao;
import br.manga.model.Manga;

public record AvaliacaoResponseDTO(
    double nota,
    String comentario,
    Manga manga
) {
    
        public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao) {
            if (avaliacao == null)
                return null;
            return new AvaliacaoResponseDTO(avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getManga());
    }
    
}
