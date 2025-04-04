package br.manga.repository;

import java.util.List;

import br.manga.model.Entrega;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntregaRepository implements PanacheRepository<Entrega> {

    public Entrega findByCodigoRastreio(String codigoRastreio) {
        return find("codigoRastreio", codigoRastreio).firstResult();
    }

    public List<Entrega> findByStatus(String status) {
        return find("statusEntrega", status).list();
    }

    public List<Entrega> findByPedido(Long idPedido) {
        return find("pedido.id", idPedido).list();
    }

    public List<Entrega> findByEndereco(String endereco) {
        return find("endereco LIKE ?1", "%" + endereco + "%").list();
    }

    public List<Entrega> findAllEntregas() {
        return listAll();
    }
}
