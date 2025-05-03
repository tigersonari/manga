package br.manga.service;

import java.util.List;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;

public interface AvaliacaoService {
    AvaliacaoResponseDTO create(AvaliacaoDTO dto);
    void update(Long id, AvaliacaoDTO dto);
    void delete(Long id);
    AvaliacaoResponseDTO findById(Long id);
    List<AvaliacaoResponseDTO> findByManga(Long mangaId);
    List<AvaliacaoResponseDTO> findByNotaGreaterThanEqual(Double nota); // avaliações com nota > ou = a nota
    Double calcularMediaNotas(Long mangaId);
    List<AvaliacaoResponseDTO> findAll();
}