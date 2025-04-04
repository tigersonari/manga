package br.manga.service;

import java.util.List;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;

public interface EntregaService {
    EntregaResponseDTO create(EntregaDTO entrega);
    void update(Long id, EntregaDTO entrega);
    void delete(Long id);
    EntregaResponseDTO findById(Long id);
    EntregaResponseDTO findByCodigoRastreio(String codigoRastreio);
    List<EntregaResponseDTO> findByStatus(String status);
    EntregaResponseDTO findByPedido (Long idPedido);
    List<EntregaResponseDTO> findAll();
}
