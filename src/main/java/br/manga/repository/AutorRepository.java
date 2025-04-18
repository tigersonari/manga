package br.manga.repository;

import java.util.List;

import br.manga.model.Autor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutorRepository implements PanacheRepository<Autor> {
    
    
    public List< Autor >findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public List<Autor> findByNacionalidade(String nacionalidade) {
        return find("nacionalidade", nacionalidade).list();
    }

     @Override
    public Autor findById(Long id){
        return find("SELECT a FROM Autor a WHERE a.id =?1 ", id).firstResult();
     }
    
}
