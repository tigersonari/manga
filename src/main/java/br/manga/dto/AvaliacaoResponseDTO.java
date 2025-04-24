package br.manga.dto;

import br.manga.model.Avaliacao;

public record AvaliacaoResponseDTO(
    double nota,
    String comentario,
    String manga
) {
    public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao) {
        if (avaliacao == null) return null;
        return new AvaliacaoResponseDTO(
            avaliacao.getNota(),
            avaliacao.getComentario(),
            avaliacao.getManga() != null ? avaliacao.getManga().getTitulo() : null
        );
    }
}