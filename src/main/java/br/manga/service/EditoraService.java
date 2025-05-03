package br.manga.service;

import java.util.List;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;

public interface EditoraService {
    EditoraResponseDTO create(EditoraDTO dto);
    void update(Long id, EditoraDTO dto);
    void delete(Long id);
    EditoraResponseDTO findById(Long id);
    List<EditoraResponseDTO> findByNome(String nome);
    List<EditoraResponseDTO> findBySede(String sede);
    List<EditoraResponseDTO> findByAnoFundacao(int ano);
    List<EditoraResponseDTO> findAll();
}