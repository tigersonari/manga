package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Autor;
import br.manga.model.Classificacao;
import br.manga.model.Editora;
import br.manga.model.Estoque;
import br.manga.model.Genero;
import br.manga.model.Manga;

public record MangaResponseDTO(
    String titulo,
    String isbn,
    LocalDate lancamento,
    Double preco,
    String sinopse,
    Estoque estoque,
    Genero genero,
    Classificacao classificacao,
    Editora editora,
    Autor autor
) {
    public static MangaResponseDTO valueOf(Manga manga) {
        if (manga == null) return null;
        return new MangaResponseDTO(
            manga.getTitulo(),
            manga.getIsbn(),
            manga.getLancamento(),
            manga.getPreco(),
            manga.getSinopse(),
            manga.getEstoque(),
            manga.getGenero(),
            manga.getClassificacao(),
            manga.getEditora(),
            manga.getAutor()
        );
    }
}