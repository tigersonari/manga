package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import br.manga.model.Autor;
import br.manga.model.Avaliacao;
import br.manga.model.Classificacao;
import br.manga.model.Edicao;
import br.manga.model.Editora;
import br.manga.model.Estoque;
import br.manga.model.Genero;
import br.manga.model.Manga;

public record MangaResponseDTO(
     Long id,
     String titulo,
     String isbn,
     LocalDate lancamento,
     String sinopse,
     Estoque estoque,
     Genero genero,
     Classificacao classificacao,
     List<Edicao> edicao,
     Editora editora,
     Autor autor,
     List<Avaliacao> avaliacao
) {
    
        public static MangaResponseDTO valueOf(Manga manga) {
            if (manga == null)
                return null;
            return new MangaResponseDTO(manga.getId(), manga.getTitulo(), manga.getIsbn(), manga.getLancamento(), manga.getSinopse(),
                manga.getEstoque(), manga.getGenero(), manga.getClassificacao(), manga.getEdicao(), manga.getEditora(),  manga.getAutor(),
                manga.getAvaliacao());
    }
    
}
