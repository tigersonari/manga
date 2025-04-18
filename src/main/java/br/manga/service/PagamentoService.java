package br.manga.service;

import java.util.List;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;

public interface PagamentoService {
    PagamentoResponseDTO create(PagamentoDTO dto);
    void update(Long id, PagamentoDTO dto);
    void delete(Long id);
    PagamentoResponseDTO findById(Long id);
    List<PagamentoResponseDTO> findByPedido(Long idPedido);
    List<PagamentoResponseDTO> findAll();
}