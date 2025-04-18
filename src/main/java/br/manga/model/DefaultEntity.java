

package br.manga.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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

    @Column(nullable = false) // Adicionado para garantir que não seja nulo
    private LocalDateTime dataCadastro; // Renomeado (era dataMangaCadastro)

    private LocalDateTime dataAlteracao; // Renomeado (era dataMangaAlteracao)

    @PrePersist
    private void registrarDataCadastro() {
        dataCadastro = LocalDateTime.now();
    }

    @PreUpdate
    private void registrarDataAlteracao() {
        dataAlteracao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

}




/*package br.manga.model;

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

    

}*/
