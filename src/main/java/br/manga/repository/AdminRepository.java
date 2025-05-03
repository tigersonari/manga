package br.manga.repository;

import java.util.List;

import br.manga.model.Admin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepository implements PanacheRepository<Admin> {
    
    public List<Admin> findByPermissao(String permissao) {
        return find("permissao", permissao).list();
    }
}