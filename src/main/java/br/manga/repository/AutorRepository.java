package br.manga.repository;

import java.util.List;

import br.manga.model.Autor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutorRepository implements PanacheRepository<Autor> {
    
    public List<Autor> findByNome(String nome) {
        return find("nome", nome).list();
    }

    public List<Autor> findByNacionalidade(String nacionalidade) {
        return find("nacionalidade", nacionalidade).list();
    }

    public Autor findById(Long id) {
        return find("id", id).firstResult();
    }
}