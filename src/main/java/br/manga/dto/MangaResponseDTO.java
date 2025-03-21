package br.manga.dto;

import br.manga.model.Editora;
import br.manga.model.Manga;

public record MangaResponseDTO (Long id, String titulo, String sigla, Editora editora) {
    
    public static MangaResponseDTO valueOf(Manga manga){

        if (manga == null)
        return null;
        return new MangaResponseDTO(manga.getId(), manga.getTitulo(), manga.getSigla(), manga.getEditora());
        }
    }
