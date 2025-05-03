package br.manga.dto;

import br.manga.model.Avaliacao;

public record AvaliacaoResponseDTO(
    Long id,
    Double nota,
    String comentario,
    Long mangaId,
    String mangaTitulo
) {
    public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao) {
        if (avaliacao == null) return null;
        return new AvaliacaoResponseDTO(
            avaliacao.getId(),
            avaliacao.getNota(),
            avaliacao.getComentario(),
            avaliacao.getManga() != null ? avaliacao.getManga().getId() : null,
            avaliacao.getManga() != null ? avaliacao.getManga().getTitulo() : null
        );
    }
}