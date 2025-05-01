package br.manga.repository;

import br.manga.model.Admin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepository implements PanacheRepository<Admin> {

    public Admin findByNome(String nome) {
        return find("nome LIKE ?1 AND tipo = 'ADMIN'", "%" + nome + "%").firstResult();
    }
}