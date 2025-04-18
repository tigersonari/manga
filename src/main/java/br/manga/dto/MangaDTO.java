package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MangaDTO(
    @NotBlank String titulo,
    @Size(min = 13, max = 13) String isbn,
    LocalDate lancamento,
    Double preco,
    String sinopse,
    Long idEstoque,
    Long idGenero,
    Long idClassificacao,
    Long idEditora,
    Long idAutor,
    List<Long> idsEdicoes
) {}




/*public record MangaDTO(
    @NotBlank String titulo,
    @Size(min = 13, max = 13) String isbn,
    LocalDate lancamento,
    @NotBlank String sinopse,
    Integer estoqueId, 
    Integer generoId,  
    Integer classificacaoId,
    Long editoraId,
    Long autorId
    
) {}*/