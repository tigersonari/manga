package br.manga.service;

import java.util.List;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;
import br.manga.model.Entrega;
import br.manga.model.Pedido;
import br.manga.repository.EntregaRepository;
import br.manga.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EntregaServiceImpl implements EntregaService {

    @Inject EntregaRepository repository;
    @Inject PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public EntregaResponseDTO create(EntregaDTO dto) {
        
        if (dto.pedidoId() == null) {
            throw new WebApplicationException("pedidoId é obrigatório", Response.Status.BAD_REQUEST);
        }
        Pedido pedido = pedidoRepository.findById(dto.pedidoId());
        if (pedido == null) {
            throw new WebApplicationException("Pedido com id " + dto.pedidoId() + " não encontrado", Response.Status.NOT_FOUND);
        }
        
        if (repository.findByPedidoId(dto.pedidoId()) != null) {
            throw new WebApplicationException("Já existe uma entrega para o pedido com id " + dto.pedidoId(), Response.Status.BAD_REQUEST);
        }

        Entrega entrega = new Entrega();
        entrega.setEndereco(dto.endereco());
        entrega.setCodigoRastreio(dto.codigoRastreio());
        entrega.setStatus(dto.status());
        entrega.setPedido(pedido);
        repository.persist(entrega);
        return EntregaResponseDTO.valueOf(entrega);
    }

    @Override
    @Transactional
    public void update(Long id, EntregaDTO dto) {
        Entrega entrega = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Entrega não encontrada"));
        entrega.setEndereco(dto.endereco());
        entrega.setCodigoRastreio(dto.codigoRastreio());
        entrega.setStatus(dto.status());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Entrega não encontrada");
        }
    }

    @Override
    public EntregaResponseDTO findById(Long id) {
        return EntregaResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Entrega não encontrada"))
        );
    }

    @Override
    public List<EntregaResponseDTO> findByStatus(String status) {
        return repository.find("status", status)
            .stream()
            .map(EntregaResponseDTO::valueOf)
            .toList();
    }

    @Override
    public EntregaResponseDTO findByCodigoRastreio(String codigo) {
        return EntregaResponseDTO.valueOf(
            repository.find("codigoRastreio", codigo).firstResult()
        );
    }

    @Override
    public EntregaResponseDTO findByPedido(Long pedidoId) {
        return EntregaResponseDTO.valueOf(
            repository.find("pedido.id", pedidoId).firstResult()
        );
    }

    @Override
    public List<EntregaResponseDTO> findAll() {
        return repository.listAll()
            .stream()
            .map(EntregaResponseDTO::valueOf)
            .toList();
    }
}