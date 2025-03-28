package br.manga.repository;


import java.util.List;

import br.manga.model.Manga;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MangaRepository implements PanacheRepository<Manga>{

    public MangaRepository() {
    }
    

        @Override
    public Manga findById(Long id){
        return find("SELECT m FROM Manga m WHERE m.id =?1 ", id).firstResult();
     }

    public List<Manga> findByTitulo(String titulo) {
    return find("SELECT m FROM Manga m WHERE m.titulo LIKE ?1", "%" + titulo + "%").firstResult();
}

public List<Manga> findByAutor(Long idAutor) {
    return find("SELECT m FROM Manga m WHERE m.autor.id = ?1", idAutor).firstResult();
}

public List<Manga> findByEditora(Long idEditora) {
    return find("SELECT m FROM Manga m WHERE m.editora.id = ?1", idEditora).firstResult();
}

public List<Manga> findByGenero(Long idGenero) {
    return find("SELECT m FROM Manga m WHERE m.genero.id = ?1", idGenero).list();
}

public List<Manga> findAllMangas() {
    return listAll();
}


}