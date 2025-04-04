package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;
import br.manga.model.Pagamento;
import br.manga.repository.PagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public PagamentoResponseDTO create(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(pagamentoDTO.metodoPagamento());
        pagamento.setStatus(pagamentoDTO.status());
        pagamento.setDataConfirmacao(pagamentoDTO.dataConfirmacao());

        pagamentoRepository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void update(Long id, PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        pagamento.setStatus(pagamentoDTO.status());
        pagamento.setDataConfirmacao(pagamentoDTO.dataConfirmacao());

        pagamentoRepository.persist(pagamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        pagamentoRepository.delete(pagamento);
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        return PagamentoResponseDTO.valueOf(pagamentoRepository.findById(id));
    }

    @Override
    public List<PagamentoResponseDTO> findByMetodo(String metodo) {
        return pagamentoRepository.findByMetodo(metodo)
                .stream().map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PagamentoResponseDTO> findByStatus(String status) {
        return pagamentoRepository.findByStatus(status)
                .stream().map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PagamentoResponseDTO> findByPedido(Long idPedido) {
        return pagamentoRepository.findByPedido(idPedido)
                .stream().map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PagamentoResponseDTO> findAll() {
        return pagamentoRepository.findAllPagamentos()
                .stream().map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
