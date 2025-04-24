package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import br.manga.model.Manga;

public record MangaResponseDTO(
    String titulo,
    String isbn,
    LocalDate lancamento,
    Double preco,
    String sinopse,
    String estoque,
    String genero,
    String classificacao,
    String editora,
    String autor,
    List<String> edicoes
) {
    public static MangaResponseDTO valueOf(Manga manga) {
        if (manga == null) return null;
        return new MangaResponseDTO(
            manga.getTitulo(),
            manga.getIsbn(),
            manga.getLancamento(),
            manga.getPreco(),
            manga.getSinopse(),
            manga.getEstoque() != null ? manga.getEstoque().name() : null,
            manga.getGenero() != null ? manga.getGenero().name() : null,
            manga.getClassificacao() != null ? manga.getClassificacao().name() : null,
            manga.getEditora() != null ? manga.getEditora().getNome() : null,
            manga.getAutor() != null ? manga.getAutor().getNome() : null,
            manga.getEdicoes() != null ? manga.getEdicoes().stream().map(e -> e.getTitulo()).toList() : null
        );
    }
}