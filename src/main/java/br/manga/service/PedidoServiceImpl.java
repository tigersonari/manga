package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
import br.manga.model.Manga;
import br.manga.model.Pedido;
import br.manga.model.PedidoManga;
import br.manga.model.Usuario;
import br.manga.repository.PedidoRepository;
import br.manga.repository.UsuarioRepository;
import br.manga.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

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
        
        if (dto.usuarioId() == null) {
            throw new WebApplicationException("usuarioId é obrigatório", Response.Status.BAD_REQUEST);
        }
        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) {
            throw new WebApplicationException("Usuário com id " + dto.usuarioId() + " não encontrado", Response.Status.NOT_FOUND);
        }

        
        if (dto.itens() == null || dto.itens().isEmpty()) {
            throw new WebApplicationException("O pedido deve conter pelo menos um item", Response.Status.BAD_REQUEST);
        }

        
        if (pedidoRepository.findByNumeroPedido(dto.numeroPedido()) != null) {
            throw new WebApplicationException("Número do pedido já cadastrado", Response.Status.BAD_REQUEST);
        }

       
        if (!List.of("PROCESSANDO", "ENVIADO", "ENTREGUE", "CANCELADO").contains(dto.status())) {
            throw new WebApplicationException("Status inválido. Use: PROCESSANDO, ENVIADO, ENTREGUE, CANCELADO", Response.Status.BAD_REQUEST);
        }

        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(dto.numeroPedido());
        pedido.setData(dto.data());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
        pedido.setUsuario(usuario);

        
        pedidoRepository.persist(pedido);

        
        List<PedidoManga> itens = dto.itens().stream().map(item -> {
            Manga manga = mangaRepository.findById(item.mangaId());
            if (manga == null) {
                throw new WebApplicationException("Mangá com id " + item.mangaId() + " não encontrado", Response.Status.NOT_FOUND);
            }
            PedidoManga pedidoManga = new PedidoManga();
            pedidoManga.setPedidoEntity(pedido);
            pedidoManga.setMangaEntity(manga);
            pedidoManga.setQuantidade(item.quantidade());
            return pedidoManga;
        }).collect(Collectors.toList());

        pedido.setMangasComprados(itens);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND));
        
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!pedidoRepository.deleteById(id)) {
            throw new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND);
        }
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND);
        }
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findByUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuario(usuarioId).stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<PedidoResponseDTO> findByStatus(String status) {
        return pedidoRepository.findByStatus(status).stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public PedidoResponseDTO findByNumeroPedido(Long numeroPedido) {
        Pedido pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
        if (pedido == null) {
            throw new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND);
        }
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.listAll().stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }
}