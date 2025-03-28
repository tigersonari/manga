package br.manga.repository;


import java.util.List;

import br.manga.model.Manga;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;



@ApplicationScoped
public class MangaRepository implements PanacheRepository<Manga> {

    public Manga findByTitulo(String titulo) {
        return find("titulo", titulo).firstResult(); 
    }

    public List<Manga> findMangasByAutor(String autor) {
        return find("SELECT m FROM Manga m WHERE m.autor.nome = ?1", autor).list(); 
    }

    // Busca mangas pelo nome da editora
    public List<Manga> findMangaByEditora(String editora) {
        return find("SELECT m FROM Manga m WHERE m.editora.nome = ?1", editora).list(); 
    }
    public List<Manga> findByGenero(String genero) {
        return find("SELECT m FROM Manga m WHERE m.genero = ?1", genero).list(); 
    }

    public List<Manga> findAllMangas() {
        return listAll();
    }
}



