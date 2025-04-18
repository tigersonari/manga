package br.manga.service;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;

public interface MangaService {
    MangaResponseDTO create(MangaDTO dto);
    void update(Long id, MangaDTO dto);
    void delete(Long id);
    MangaResponseDTO findById(Long id);
    List<MangaResponseDTO> findByTitulo(String titulo);
    List<MangaResponseDTO> findByGenero(Long idGenero);
    List<MangaResponseDTO> findByClassificacao(Long idClassificacao);
    List<MangaResponseDTO> findByEditora(Long idEditora);
    List<MangaResponseDTO> findAll();
}