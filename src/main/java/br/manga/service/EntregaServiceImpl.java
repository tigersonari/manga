package br.manga.service;

import java.util.List;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;
import br.manga.model.Entrega;
import br.manga.repository.EntregaRepository;
import br.manga.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EntregaServiceImpl implements EntregaService {

    @Inject
    EntregaRepository entregaRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public EntregaResponseDTO create(EntregaDTO dto) {
        Entrega entrega = new Entrega();
        entrega.setEndereco(dto.endereco());
        entrega.setCodigoRastreio(dto.codigoRastreio());
        entrega.setStatus(dto.status());
        entrega.setPedido(pedidoRepository.findById(dto.pedidoId()));

        entregaRepository.persist(entrega);
        return EntregaResponseDTO.valueOf(entrega);
    }

    @Override
    @Transactional
    public void update(Long id, EntregaDTO dto) {
        Entrega entrega = entregaRepository.findById(id);
        entrega.setEndereco(dto.endereco());
        entrega.setCodigoRastreio(dto.codigoRastreio());
        entrega.setStatus(dto.status());
        entrega.setPedido(pedidoRepository.findById(dto.pedidoId()));
    }

    @Override
    @Transactional
    public EntregaResponseDTO findByCodigoRastreio(String codigoRastreio) {
        return EntregaResponseDTO.valueOf(entregaRepository.findByCodigoRastreio(codigoRastreio));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entregaRepository.deleteById(id);
    }

    @Override
    public EntregaResponseDTO findById(Long id) {
        return EntregaResponseDTO.valueOf(entregaRepository.findById(id));
    }

    @Override
    public List<EntregaResponseDTO> findByPedido(Long pedidoId) {
        return entregaRepository.findByPedido(pedidoId).stream()
            .map(EntregaResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EntregaResponseDTO> findAll() {
        return entregaRepository.listAll().stream()
            .map(EntregaResponseDTO::valueOf)
            .toList();
    }
}