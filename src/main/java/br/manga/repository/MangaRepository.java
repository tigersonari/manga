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
        return find("SELECT m FROM Manga m WHERE m.nome LIKE ?1 ", "%" + titulo + "%").list();
    }


       /*  public List<Manga> findByAvaliacao(Long avaliacaoId) {
            return find("SELECT m FROM Manga m JOIN m.avaliacoes a WHERE a.id = ?1", avaliacaoId).list();
        }*/

        public Manga findByIdWithAvaliacoes(Long mangaId) {
            return find("SELECT m FROM Manga m LEFT JOIN FETCH m.avaliacoes WHERE m.id = ?1", mangaId).firstResult();
        }

        public List<Manga> findMangaByEditora(Long idEditora) {
            return find("SELECT m FROM Manga m JOIN FETCH m.editora e WHERE e.id = ?1", idEditora).list();
    }

    public List<Manga> findMangasByAutor(Long idAutor) {
        return find("SELECT m FROM Manga m WHERE m.autor.id = ?1", idAutor).list();
    }

    public List<Manga> findMangasByGenero(Long idGenero) {
        return find("SELECT m FROM Manga m WHERE m.genero.id = ?1", idGenero).list();
    }

        public List<Manga> findAllMangas() {
            return listAll(); 
        }

}