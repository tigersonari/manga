package br.manga.service;

import java.util.List;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;

public interface AutorService {
    AutorResponseDTO create(AutorDTO autor);
    void update(Long id, AutorDTO autor);
    void delete(Long id);
    AutorResponseDTO findById(Long id);
    AutorResponseDTO findByNome(String nome);
    List<AutorResponseDTO> findByNacionalidade(String nacionalidade);
    List<AutorResponseDTO> findAll();
}
