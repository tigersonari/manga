package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
import br.manga.model.Manga;
import br.manga.model.Pedido;
import br.manga.model.PedidoManga;
import br.manga.model.Usuario;
import br.manga.repository.MangaRepository;
import br.manga.repository.PedidoRepository;
import br.manga.repository.UsuarioRepository;
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
        
        if (dto.usuarioId() == null) throw new WebApplicationException("usuarioId é obrigatório", Response.Status.BAD_REQUEST);
        if (dto.numeroPedido() == null) throw new WebApplicationException("Número do pedido é obrigatório", Response.Status.BAD_REQUEST);
        if (dto.data() == null) throw new WebApplicationException("Data do pedido é obrigatória", Response.Status.BAD_REQUEST);
        if (dto.valorTotal() == null || dto.valorTotal() < 0) throw new WebApplicationException("Valor total inválido", Response.Status.BAD_REQUEST);
        if (dto.itens() == null || dto.itens().isEmpty()) throw new WebApplicationException("O pedido deve conter pelo menos um item", Response.Status.BAD_REQUEST);

        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) throw new WebApplicationException("Usuário não encontrado", Response.Status.NOT_FOUND);

        if (pedidoRepository.findByNumeroPedido(dto.numeroPedido()) != null) {
            throw new WebApplicationException("Número do pedido já cadastrado", Response.Status.BAD_REQUEST);
        }

        if (!List.of("PROCESSANDO", "ENVIADO", "ENTREGUE", "CANCELADO").contains(dto.status())) {
            throw new WebApplicationException("Status inválido", Response.Status.BAD_REQUEST);
        }

        
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(dto.numeroPedido());
        pedido.setData(dto.data());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());
        pedido.setUsuario(usuario);

        
        List<PedidoManga> itens = dto.itens().stream().map(item -> {
            Manga manga = mangaRepository.findById(item.mangaId());
            if (manga == null) throw new WebApplicationException("Mangá não encontrado", Response.Status.NOT_FOUND);
            if (item.quantidade() <= 0) throw new WebApplicationException("Quantidade inválida", Response.Status.BAD_REQUEST);

            PedidoManga pedidoManga = new PedidoManga();
            pedidoManga.setPedido(pedido.getId());
            pedidoManga.setManga(manga.getId());
            pedidoManga.setQuantidade(item.quantidade());
            return pedidoManga;
        }).collect(Collectors.toList());

        pedido.setMangasComprados(itens);
        pedidoRepository.persist(pedido);
        
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND));

        
        if (!List.of("PROCESSANDO", "ENVIADO", "ENTREGUE", "CANCELADO").contains(dto.status())) {
            throw new WebApplicationException("Status inválido", Response.Status.BAD_REQUEST);
        }

        if (dto.valorTotal() == null || dto.valorTotal() < 0) {
            throw new WebApplicationException("Valor total inválido", Response.Status.BAD_REQUEST);
        }

        
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());

        if (dto.itens() != null && !dto.itens().isEmpty()) {
            pedido.getMangasComprados().clear();
            
            List<PedidoManga> itens = dto.itens().stream().map(item -> {
                Manga manga = mangaRepository.findById(item.mangaId());
                if (manga == null) throw new WebApplicationException("Mangá não encontrado", Response.Status.NOT_FOUND);
                if (item.quantidade() <= 0) throw new WebApplicationException("Quantidade inválida", Response.Status.BAD_REQUEST);

                PedidoManga pedidoManga = new PedidoManga();
                pedidoManga.setPedido(pedido.getId());
                pedidoManga.setManga(manga.getId());
                pedidoManga.setQuantidade(item.quantidade());
                return pedidoManga;
            }).collect(Collectors.toList());
            
            pedido.setMangasComprados(itens);
        }
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
        if (pedido == null) throw new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND);
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
        if (pedido == null) throw new WebApplicationException("Pedido não encontrado", Response.Status.NOT_FOUND);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.listAll().stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }
}