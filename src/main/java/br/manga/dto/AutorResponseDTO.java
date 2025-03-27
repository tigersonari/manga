package br.manga.dto;

import java.util.List;

import br.manga.model.Autor;
import br.manga.model.Manga;

public record AutorResponseDTO(
    String nome,
    String nacionalidade,
    List<Manga> manga
    ) {
        
            public static AutorResponseDTO valueOf(Autor autor) {
                if (autor == null)
                    return null;
                return new AutorResponseDTO(autor.getNome(), autor.getNacionalidade(), autor.getManga());
    }
}
