package br.manga.repository;


import br.manga.model.Manga;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MangashRepository implements PanacheRepository<Manga>{
    
    public Manga findBySigla(String sigla){
       return find("SELECT e FROM Manga e WHERE e.sigla = ?1 ", sigla).firstResult();
    }

        @Override
    public Manga findById(Long id){
        return find("SELECT e FROM Manga e WHERE e.id =?1 ", id).firstResult();
     }

}