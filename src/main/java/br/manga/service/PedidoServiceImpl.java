
package br.manga.service;

import java.util.ArrayList;
import java.util.List;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
import br.manga.model.Manga;
import br.manga.model.Pedido;
import br.manga.model.PedidoManga;
import br.manga.repository.MangaRepository;
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


    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(dto.numeroPedido());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
        pedido.setUsuario(usuarioRepository.findById(dto.idUsuario()));

        pedidoRepository.persist(pedido);

        if (dto.idsMangas() != null) {
            List<PedidoManga> pedidoMangas = new ArrayList<>();
            for (Long mangaId : dto.idsMangas()) {
                Manga manga = mangaRepository.findById(mangaId);
                if (manga != null) {
                    PedidoManga pedidoManga = new PedidoManga();
                    pedidoManga.setPedidoEntity(pedido);
                    pedidoManga.setMangaEntity(manga);
                    pedidoManga.setPedido(pedido.getId());
                    pedidoManga.setManga(mangaId);
                    pedidoMangas.add(pedidoManga);
                }
            }
            pedido.setMangasComprados(pedidoMangas);
        }

        

        if (pedido.getMangasComprados() != null) {
            for (PedidoManga pedidoManga : pedido.getMangasComprados()) {
                pedidoRepository.getEntityManager().persist(pedidoManga);
            }
        }

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado: " + id);
        }
        pedido.setNumeroPedido(dto.numeroPedido());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
        pedido.setUsuario(usuarioRepository.findById(dto.idUsuario()));

        if (dto.idsMangas() != null) {
            pedido.getMangasComprados().clear();
            List<PedidoManga> pedidoMangas = new ArrayList<>();
            for (Long mangaId : dto.idsMangas()) {
                Manga manga = mangaRepository.findById(mangaId);
                if (manga != null) {
                    PedidoManga pedidoManga = new PedidoManga();
                    pedidoManga.setPedidoEntity(pedido);
                    pedidoManga.setMangaEntity(manga);
                    pedidoManga.setPedido(pedido.getId());
                    pedidoManga.setManga(mangaId);
                    pedidoMangas.add(pedidoManga);
                }
            }
            pedido.setMangasComprados(pedidoMangas);
        }

        pedidoRepository.persist(pedido);
        if (pedido.getMangasComprados() != null) {
            for (PedidoManga pedidoManga : pedido.getMangasComprados()) {
                pedidoRepository.getEntityManager().persist(pedidoManga);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado: " + id);
        }
        return PedidoResponseDTO.valueOf(pedido);
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