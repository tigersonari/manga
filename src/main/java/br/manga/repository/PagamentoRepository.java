package br.manga.repository;

import java.util.List;

import br.manga.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

    public List<Pagamento> findByMetodo(String metodo) {
        return find("metodoPagamento", metodo).list();
    }

    public List<Pagamento> findByStatus(String status) {
        return find("statusPagamento", status).list();
    }

    public List<Pagamento> findByPedidoId(Long idPedido) {
        return find("pedido.id", idPedido).list();
    }

    public List<Pagamento> findAllPagamentos() {
        return listAll();
    }
}

