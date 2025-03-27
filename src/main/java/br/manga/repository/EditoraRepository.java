package br.manga.repository;


import java.util.List;

import br.manga.model.Editora;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EditoraRepository implements PanacheRepository<Editora>{

    public EditoraRepository() {
    }
    

        @Override
        public Editora findById(Long id){
            return find("SELECT e FROM Editora e WHERE e.id =?1 ", id).firstResult();
     }
    
        public List<Editora> findByNome(String nome) {
        return find("SELECT e FROM Editora e WHERE e.nome LIKE ?1 ", "%" + nome + "%").list();
    }
     
    
        public List<Editora> findAllEditoras() {
            return listAll(); 
    }
}