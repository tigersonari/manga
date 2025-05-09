package br.manga.repository;

import java.util.List;

import br.manga.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    
    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public List<Usuario> findByNome(String nome) {
        return find("nome like ?1", "%"+nome+"%").list();
    }
}