package br.manga.service;

import java.util.List;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;

public interface PedidoService {
    PedidoResponseDTO create(PedidoDTO dto);
    void update(Long id, PedidoDTO dto);
    void delete(Long id);
    PedidoResponseDTO findById(Long id);
    List<PedidoResponseDTO> findByUsuario(Long idUsuario);
    List<PedidoResponseDTO> findAll();
}