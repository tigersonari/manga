package br.manga.repository;

import java.util.List;

import br.manga.model.Avaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepository<Avaliacao> {

    
    public List<Avaliacao> findByManga(Long idManga) {
        return find("SELECT a FROM Avaliacao a WHERE a.manga.id = ?1", idManga).list();
    }

}
