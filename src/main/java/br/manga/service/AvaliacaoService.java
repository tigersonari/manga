package br.manga.service;

import java.util.List;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;

public interface AvaliacaoService {
    AvaliacaoResponseDTO create(AvaliacaoDTO avaliacao);
    void update(Long id, AvaliacaoDTO avaliacao);
    void delete(Long id);
    AvaliacaoResponseDTO findById(Long id);
    List<AvaliacaoResponseDTO> findAll();
}
