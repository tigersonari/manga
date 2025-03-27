package br.manga.repository;

import java.util.List;

import br.manga.model.Edicao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EdicaoRepository implements PanacheRepository<Edicao> {

   
    public List<Edicao> findEdicoesByManga(Long idManga) {
        return find("SELECT e FROM Edicao e WHERE e.manga.id = ?1", idManga).list();
    }

    
    public Edicao findByMangaAndVolume(Long idManga, Integer volume) {
        return find("SELECT e FROM Edicao e WHERE e.manga.id = ?1 AND e.volume = ?2", idManga, volume).firstResult();
    }
}
