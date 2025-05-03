package br.manga.service;

import java.util.List;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;
import br.manga.model.Pagamento;
import br.manga.model.Pedido;
import br.manga.repository.PagamentoRepository;
import br.manga.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PagamentoResponseDTO create(PagamentoDTO dto) {
        
        if (dto.pedidoId() == null) {
            throw new WebApplicationException("pedidoId é obrigatório", Response.Status.BAD_REQUEST);
        }
        Pedido pedido = pedidoRepository.findById(dto.pedidoId());
        if (pedido == null) {
            throw new WebApplicationException("Pedido com id " + dto.pedidoId() + " não encontrado", Response.Status.NOT_FOUND);
        }
        
        if (repository.findByPedidoId(dto.pedidoId()) != null) {
            throw new WebApplicationException("Já existe um pagamento para o pedido com id " + dto.pedidoId(), Response.Status.BAD_REQUEST);
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(dto.metodoPagamento());
        pagamento.setStatus(dto.status());
        pagamento.setDataConfirmacao(dto.dataConfirmacao());
        pagamento.setPedido(pedido);
        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void update(Long id, PagamentoDTO dto) {
        Pagamento pagamento = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Pagamento não encontrado"));
        
        pagamento.setStatus(dto.status());
        pagamento.setDataConfirmacao(dto.dataConfirmacao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Pagamento não encontrado");
        }
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        return PagamentoResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado"))
        );
    }

    @Override
    public List<PagamentoResponseDTO> findByStatus(String status) {
        return repository.findByStatus(status).stream()
            .map(PagamentoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public PagamentoResponseDTO findByPedido(Long pedidoId) {
        return PagamentoResponseDTO.valueOf(repository.findByPedidoId(pedidoId));
    }

    @Override
    public List<PagamentoResponseDTO> findAll() {
        return repository.listAll().stream()
            .map(PagamentoResponseDTO::valueOf)
            .toList();
    }
}