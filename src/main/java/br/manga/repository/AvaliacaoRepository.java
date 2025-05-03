package br.manga.repository;

import java.util.List;

import br.manga.model.Avaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepository<Avaliacao> {
    
    public List<Avaliacao> findByMangaId(Long mangaId) {
        return find("manga.id", mangaId).list();
    }

    public List<Avaliacao> findByNotaGreaterThanEqual(Double nota) {
        return find("nota >= ?1", nota).list();
    }

    public Double calcularMediaNotas(Long mangaId) {
        return find("select avg(nota) from Avaliacao where manga.id = ?1", mangaId)
            .project(Double.class)
            .firstResult();
    }
}