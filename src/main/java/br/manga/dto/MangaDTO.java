package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MangaDTO(
    @NotBlank String titulo,
    @Size(min = 13, max = 13) String isbn,
    LocalDate lancamento,
    Double preco,
    String sinopse,
    Integer idEstoque,
    Integer idGenero,
    Integer idClassificacao,
    Long idEditora,
    Long idAutor
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