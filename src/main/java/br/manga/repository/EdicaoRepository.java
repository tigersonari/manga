package br.manga.repository;

import java.util.List;

import br.manga.model.Edicao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EdicaoRepository implements PanacheRepository<Edicao> {
    
    public List<Edicao> findByMangaId(Long mangaId) {
        return find("manga.id", mangaId).list();
    }

    public List<Edicao> findByFormato(Integer formatoId) {
        return find("formato.id", formatoId).list();
    }

    public List<Edicao> findByStatus(Integer statusId) {
        return find("status.id", statusId).list();
    }

    public Edicao findByVolumeAndManga(Integer volume, Long mangaId) {
        return find("volume = ?1 and manga.id = ?2", volume, mangaId).firstResult();
    }
}