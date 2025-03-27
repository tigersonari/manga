package br.manga.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public class DefaultEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataMangaCadastro;
    private LocalDateTime dataMangaAlteracao;

    @PrePersist
    private void registrarDataMangaCadastro() {
        dataMangaCadastro = LocalDateTime.now();
    }

    @PreUpdate
    private void registrarDataMangaAlteracao() {
        dataMangaAlteracao = LocalDateTime.now();
    }

    /*quando se altera o mangá, altera-se também sua herança, seu defaultEntity. pois mangá é um defaultEntity */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataMangaCadastro() {
        return dataMangaCadastro;
    }

    public void setDataMangaCadastro(LocalDateTime dataMangaCadastro) {
        this.dataMangaCadastro = dataMangaCadastro;
    }

    public LocalDateTime getDataMangaAlteracao() {
        return dataMangaAlteracao;
    }

    public void setDataMangaAlteracao(LocalDateTime dataMangaAlteracao) {
        this.dataMangaAlteracao = dataMangaAlteracao;
    }

    

}
