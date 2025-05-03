package br.manga.repository;

import java.util.List;

import br.manga.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    
    public List<Pedido> findByUsuario(Long usuarioId) {
        return find("usuario.id", usuarioId).list();
    }

    public List<Pedido> findByStatus(String status) {
        return find("status", status).list();
    }

    public Pedido findByNumeroPedido(Long numeroPedido) {
        return find("numeroPedido", numeroPedido).firstResult();
    }
}