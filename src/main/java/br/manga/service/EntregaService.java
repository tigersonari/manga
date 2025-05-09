package br.manga.service;

import java.util.List;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;

public interface EntregaService {
    EntregaResponseDTO create(EntregaDTO dto);
    void update(Long id, EntregaDTO dto);
    void delete(Long id);
    EntregaResponseDTO findById(Long id);
    List<EntregaResponseDTO> findByStatus(String status);
    EntregaResponseDTO findByCodigoRastreio(String codigo);
    EntregaResponseDTO findByPedido(Long pedidoId);
    List<EntregaResponseDTO> findAll();
}