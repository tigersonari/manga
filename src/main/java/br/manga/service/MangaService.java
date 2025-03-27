package br.manga.service;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;

public interface MangaService {
    MangaResponseDTO create(MangaDTO manga);
    void update(Long id, MangaDTO manga);
    void delete(Long id);
    MangaResponseDTO findById(Long id);
    MangaResponseDTO findByTitulo(String titulo);
    List<MangaResponseDTO> findByAutor(String autor);
    List<MangaResponseDTO> findByEditora(String editora);
    List<MangaResponseDTO> findByGenero(String genero);
    List<MangaResponseDTO> findAll();
}
