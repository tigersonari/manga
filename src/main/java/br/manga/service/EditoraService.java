package br.manga.service;

import java.time.LocalDate;
import java.util.List;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;

public interface EditoraService {
    EditoraResponseDTO create(EditoraDTO editora);
    void update(Long id, EditoraDTO editora);
    void delete(Long id);
    EditoraResponseDTO findById(Long id);
    List<EditoraResponseDTO> findByNome(String nome);  
    List<EditoraResponseDTO> findBySede(String sede);
    List<EditoraResponseDTO> findByFundacao(LocalDate fundacao);
    List<EditoraResponseDTO> findAll();
}
