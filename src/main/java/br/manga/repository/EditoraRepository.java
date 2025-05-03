package br.manga.repository;

import java.util.List;

import br.manga.model.Editora;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EditoraRepository implements PanacheRepository<Editora> {
    
    public List<Editora> findByNome(String nome) {
        return find("nome like ?1", "%"+nome+"%").list();
    }

    public List<Editora> findBySede(String sede) {
        return find("sede", sede).list();
    }

    public List<Editora> findByAnoFundacao(int ano) {
        return find("year(fundacao) = ?1", ano).list();
    }
}