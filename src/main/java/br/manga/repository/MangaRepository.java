package br.manga.repository;

import java.util.List;

import br.manga.model.Manga;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MangaRepository implements PanacheRepository<Manga> {
    
    public List<Manga> findByTitulo(String titulo) {
        return find("titulo like ?1", "%"+titulo+"%").list();
    }

    public List<Manga> findByGenero(Integer generoId) {
        return find("genero.id", generoId).list();
    }

    public List<Manga> findByEditora(Long editoraId) {
        return find("editora.id", editoraId).list();
    }

    public Manga findByIsbn(String isbn) {
        return find("isbn", isbn).firstResult();
    }
}