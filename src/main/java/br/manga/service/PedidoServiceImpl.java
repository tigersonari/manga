package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
import br.manga.model.Manga;
import br.manga.model.Pedido;
import br.manga.repository.MangaRepository;
import br.manga.repository.PagamentoRepository;
import br.manga.repository.PedidoRepository;
import br.manga.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    MangaRepository mangaRepository;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(dto.numeroPedido());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
        pedido.setUsuario(usuarioRepository.findById(dto.idUsuario()));

        if (dto.idsMangas() != null) {
            List<Manga> mangas = dto.idsMangas().stream()
                .map(mangaId -> mangaRepository.findById(mangaId))
                .collect(Collectors.toList());
            pedido.setMangasComprados(mangas);
        }

        if (dto.idPagamento() != null) {
            pedido.setPagamento(pagamentoRepository.findById(dto.idPagamento()));
        }

        pedidoRepository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id);
        pedido.setNumeroPedido(dto.numeroPedido());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
        pedido.setUsuario(usuarioRepository.findById(dto.idUsuario()));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findByUsuario(Long idUsuario) {
        return pedidoRepository.findByUsuario(idUsuario).stream()
            .map(PedidoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.listAll().stream()
            .map(PedidoResponseDTO::valueOf)
            .toList();
    }
}