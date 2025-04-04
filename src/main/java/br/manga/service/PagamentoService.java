package br.manga.service;

import java.util.List;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;

public interface PagamentoService {
    PagamentoResponseDTO create(PagamentoDTO pagamento);
    void update(Long id, PagamentoDTO pagamento);
    void delete(Long id);
    PagamentoResponseDTO findById(Long id);
    List<PagamentoResponseDTO> findByMetodo(String metodo);
    List<PagamentoResponseDTO> findByStatus(String status);
    List<PagamentoResponseDTO> findByPedido(Long idPedido);
    List<PagamentoResponseDTO> findAll();
}
