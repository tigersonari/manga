package br.manga.repository;

import java.util.List;

import br.manga.model.Entrega;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntregaRepository implements PanacheRepository<Entrega> {
    
    public List<Entrega> findByStatus(String status) {
        return find("status", status).list();
    }

    public Entrega findByCodigoRastreio(String codigo) {
        return find("codigoRastreio", codigo).firstResult();
    }

    public Entrega findByPedidoId(Long pedidoId) {
        return find("pedido.id", pedidoId).firstResult();
    }
    
}