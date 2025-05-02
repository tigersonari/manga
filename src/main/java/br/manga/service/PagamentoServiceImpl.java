package br.manga.service;

import java.util.List;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;
import br.manga.model.Pagamento;
import br.manga.repository.PagamentoRepository;
import br.manga.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PagamentoResponseDTO create(PagamentoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(dto.metodoPagamento());
        pagamento.setStatus(dto.status());
        pagamento.setDataConfirmacao(dto.dataConfirmacao());
        pagamento.setPedido(pedidoRepository.findById(dto.idPedido()));

        pagamentoRepository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void update(Long id, PagamentoDTO dto) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        pagamento.setMetodoPagamento(dto.metodoPagamento());
        pagamento.setStatus(dto.status());
        pagamento.setDataConfirmacao(dto.dataConfirmacao());
        pagamento.setPedido(pedidoRepository.findById(dto.idPedido()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pagamentoRepository.deleteById(id);
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        return PagamentoResponseDTO.valueOf(pagamentoRepository.findById(id));
    }

    @Override
    public List<PagamentoResponseDTO> findByPedido(Long idPedido) {
        return pagamentoRepository.findByPedidoId(idPedido).stream()
            .map(PagamentoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<PagamentoResponseDTO> findAll() {
        return pagamentoRepository.listAll().stream()
            .map(PagamentoResponseDTO::valueOf)
            .toList();
    }
}