package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
import br.manga.model.Pedido;
import br.manga.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(pedidoDTO.numeroPedido());
        pedido.setData(pedidoDTO.data());
        pedido.setStatus(pedidoDTO.status());
        pedido.setMangasComprados(pedidoDTO.mangasComprados());
        pedido.setValorTotal(pedidoDTO.valorTotal());

        pedidoRepository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id);
        pedido.setStatus(pedidoDTO.status());
        pedidoRepository.persist(pedido);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        pedidoRepository.delete(pedido);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public PedidoResponseDTO findByNumeroPedido(Long numeroPedido) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findByNumeroPedido(numeroPedido));
    }

    @Override
    public List<PedidoResponseDTO> findByStatus(String status) {
        return pedidoRepository.findByStatus(status)
                .stream().map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponseDTO> findByUsuario(Long idUsuario) {
        return pedidoRepository.findByUsuario(idUsuario)
                .stream().map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAllPedidos()
                .stream().map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
