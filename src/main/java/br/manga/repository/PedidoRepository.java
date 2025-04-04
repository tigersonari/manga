package br.manga.repository;

import java.util.List;

import br.manga.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public Pedido findByNumeroPedido(Long numeroPedido) {
        return find("numeroPedido", numeroPedido).firstResult();
    }

    public List<Pedido> findByStatus(String status) {
        return find("status", status).list();
    }

    public List<Pedido> findByUsuario(Long idUsuario) {
        return find("usuario.id", idUsuario).list();
    }

    public List<Pedido> findAllPedidos() {
        return listAll();
    }
}
