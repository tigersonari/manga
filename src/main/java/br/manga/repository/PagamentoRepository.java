package br.manga.repository;

import java.util.List;

import br.manga.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {
    
    public List<Pagamento> findByStatus(String status) {
        return find("status", status).list();
    }

    public Pagamento findByPedidoId(Long pedidoId) {
        return find("pedido.id", pedidoId).firstResult();
    }

    public List<Pagamento> findByMetodoPagamento(String metodo) {
        return find("metodoPagamento", metodo).list();
    }
}