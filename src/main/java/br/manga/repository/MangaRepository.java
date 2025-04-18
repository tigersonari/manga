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

    public List<Manga> findByGenero(String genero) {
        return find("SELECT m FROM Manga m WHERE m.genero = ?1", genero).list(); 
    }

    public List<Manga> findByClassificacao(Long idClassificacao) {
        return find("classificacao.id = ?1", idClassificacao).list();
    }

    public List<Manga> findByEditora(Long idEditora) {
        return find("editora.id = ?1", idEditora).list();
    }

    public List<Manga> findAllMangas() {
        return listAll();
    }
}



