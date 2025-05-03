package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MangaDTO(
    @NotBlank(message = "título vazio")
    String titulo,
    
    @NotBlank(message = "ISBN vazio")
    @Size(min = 13, max = 13, message = " o ISBN deve ter 13 caracteres")
    String isbn,
    
    @NotNull(message = "data de lançamento vazia")
    LocalDate lancamento,
    
    @NotNull(message = "preço vazio")
    @Positive(message = "o preço deve conter valores válidos")
    Double preco,
    
    @NotBlank(message = "sinopse  vazia")
    String sinopse,
    
    @NotNull(message = " estoque vazio")
    Integer estoqueId,
    
    @NotNull(message = " deve ter gênero")
    Integer generoId,
    
    @NotNull(message = "classificação vazia")
    Integer classificacaoId,
    
    @NotNull(message = "deve conter editora")
    Long editoraId,
    
    @NotNull(message = "deve conter autor")
    Long autorId
) {}