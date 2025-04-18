package br.manga.repository;

import java.util.List;

import br.manga.model.Edicao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EdicaoRepository implements PanacheRepository<Edicao> {

    public List<Edicao> findByManga(Long idManga) {
        return find("manga.id = ?1", idManga).list();
    }
}