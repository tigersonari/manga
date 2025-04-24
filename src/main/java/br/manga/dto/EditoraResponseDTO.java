package br.manga.dto;

import java.time.LocalDate;

import br.manga.model.Editora;

public record EditoraResponseDTO(
    String nome,
    String sede,
    LocalDate fundacao
) {
    public static EditoraResponseDTO valueOf(Editora editora) {
        if (editora == null) return null;
        return new EditoraResponseDTO(
            editora.getNome(),
            editora.getSede(),
            editora.getFundacao()
        );
    }
}