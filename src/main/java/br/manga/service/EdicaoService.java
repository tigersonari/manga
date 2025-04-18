package br.manga.service;

import java.util.List;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;

public interface EdicaoService {
    EdicaoResponseDTO create(EdicaoDTO dto);
    void update(Long id, EdicaoDTO dto);
    void delete(Long id);
    EdicaoResponseDTO findById(Long id);
    List<EdicaoResponseDTO> findByManga(Long idManga);
    List<EdicaoResponseDTO> findAll();
}