package br.manga.service;

import java.util.List;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;

public interface AutorService {
    AutorResponseDTO create(AutorDTO dto);
    void update(Long id, AutorDTO dto);
    void delete(Long id);
    AutorResponseDTO findById(Long id);
    List<AutorResponseDTO> findByNome(String nome);
    List<AutorResponseDTO> findAll();
    Object findByNacionalidade(String nacionalidade);
}