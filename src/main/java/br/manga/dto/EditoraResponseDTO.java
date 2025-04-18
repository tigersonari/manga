package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Editora;

public record EditoraResponseDTO(
    Long id,
    String nome,
    String sede,
    LocalDate fundacao
) {
    public static EditoraResponseDTO valueOf(Editora editora) {
        if (editora == null) return null;
        return new EditoraResponseDTO(
            editora.getId(),
            editora.getNome(),
            editora.getSede(),
            editora.getFundacao()
        );
    }
}