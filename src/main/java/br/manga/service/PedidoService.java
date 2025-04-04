package br.manga.service;

import java.util.List;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;

public interface PedidoService {
    PedidoResponseDTO create(PedidoDTO pedido);
    void update(Long id, PedidoDTO pedido);
    void delete(Long id);
    PedidoResponseDTO findById(Long id);
    PedidoResponseDTO findByNumeroPedido(Long numeroPedido);
    List<PedidoResponseDTO> findByStatus(String status);
    List<PedidoResponseDTO> findByUsuario(Long idUsuario);
    List<PedidoResponseDTO> findAll();
}
