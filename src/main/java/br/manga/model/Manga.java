package br.manga.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Manga extends DefaultEntity {

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @Size(min = 13, max = 13)
    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDate lancamento;

    @Column(nullable = false)
    private Double preco;

    @NotBlank
    @Column(nullable = false)
    private String sinopse;

    private Estoque estoque;

    private Genero genero;

    private Classificacao classificacao;


    @ManyToOne
    @JoinColumn(name = "id_editora")
    private Editora editora;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }



    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPreco() {
        return preco;
    }
}