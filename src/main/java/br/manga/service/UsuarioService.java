package br.manga.service;

import java.util.List;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioDTO dto);
    void update(Long id, UsuarioDTO dto);
    void delete(Long id);
    UsuarioResponseDTO findById(Long id);
    List<UsuarioResponseDTO> findByNome(String nome);
    List<UsuarioResponseDTO> findAll();
}

