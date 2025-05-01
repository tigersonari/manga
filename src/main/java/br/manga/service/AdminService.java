package br.manga.service;

import java.util.List;

import br.manga.dto.AdminDTO;
import br.manga.dto.AdminResponseDTO;

public interface AdminService {
    AdminResponseDTO create(AdminDTO dto);
    void update(Long id, AdminDTO dto);
    void delete(Long id);
    AdminResponseDTO findById(Long id);
    List<AdminResponseDTO> findAll();
    AdminResponseDTO findByNome(String nome);
}