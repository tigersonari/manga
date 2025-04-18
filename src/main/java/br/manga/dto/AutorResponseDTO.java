package br.manga.dto;

import br.manga.model.Autor;

public record AutorResponseDTO(
    Long id,
    String nome,
    String nacionalidade
) {
    public static AutorResponseDTO valueOf(Autor autor) {
        if (autor == null) return null;
        return new AutorResponseDTO(
            autor.getId(),
            autor.getNome(),
            autor.getNacionalidade()
        );
    }
}