package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import br.manga.model.Editora;
import br.manga.model.Manga;

public record EditoraResponseDTO (
    String nome,
    String sede,
    LocalDate fundacao,
    List<Manga> manga
){
    public static EditoraResponseDTO valueOf(Editora editora) {
        if (editora == null)
            return null;
        return new EditoraResponseDTO(editora.getNome(), editora.getSede(), editora.getFundacao(), editora.getManga());
}
}
    


