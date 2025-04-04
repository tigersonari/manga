package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;
import br.manga.model.Entrega;
import br.manga.repository.EntregaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EntregaServiceImpl implements EntregaService {

    @Inject
    EntregaRepository entregaRepository;

    @Override
    @Transactional
    public EntregaResponseDTO create(EntregaDTO entregaDTO) {
        Entrega entrega = new Entrega();
        entrega.setEndereco(entregaDTO.endereco());
        entrega.setCodigoRastreio(entregaDTO.codigoRastreio());
        entrega.setStatus(entregaDTO.status());

        entregaRepository.persist(entrega);
        return EntregaResponseDTO.valueOf(entrega);
    }

    @Override
    @Transactional
    public void update(Long id, EntregaDTO entregaDTO) {
        Entrega entrega = entregaRepository.findById(id);
        entrega.setCodigoRastreio(entregaDTO.codigoRastreio());
        entrega.setStatus(entregaDTO.status());

        entregaRepository.persist(entrega);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Entrega entrega = entregaRepository.findById(id);
        entregaRepository.delete(entrega);
    }

    @Override
    public EntregaResponseDTO findById(Long id) {
        return EntregaResponseDTO.valueOf(entregaRepository.findById(id));
    }

    @Override
    public EntregaResponseDTO findByCodigoRastreio(String codigoRastreio) {
        return EntregaResponseDTO.valueOf(entregaRepository.findByCodigoRastreio(codigoRastreio));
    }

    @Override
    public List<EntregaResponseDTO> findByStatus(String status) {
        return entregaRepository.findByStatus(status)
                .stream().map(EntregaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public EntregaResponseDTO findByPedido(Long idPedido) {
        return EntregaResponseDTO.valueOf((Entrega) entregaRepository.findByPedido(idPedido));
    }/*? duvida */

    @Override
    public List<EntregaResponseDTO> findAll() {
        return entregaRepository.findAllEntregas()
                .stream().map(EntregaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
