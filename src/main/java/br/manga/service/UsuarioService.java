package br.manga.service;

import java.util.List;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioDTO usuario);
    void update(Long id, UsuarioDTO usuario);
    void delete(Long id);
    UsuarioResponseDTO findById(Long id);
    UsuarioResponseDTO findByEmail(String email);
    UsuarioResponseDTO findByNome(String nome);
    List<UsuarioResponseDTO> findAll();
}
