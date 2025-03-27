package br.manga.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Editora extends DefaultEntity{

    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sede;

    @Column(nullable = false)
    private LocalDate fundacao;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manga> manga = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFundacao(LocalDate fundacao) {
        this.fundacao = fundacao;
    }

    public List<Manga> getManga() {
        return manga;
    }

    public void setManga(List<Manga> manga) {
        this.manga = manga;
    }

}
