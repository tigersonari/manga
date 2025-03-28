package br.manga.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Manga extends DefaultEntity{

    
    @Column(nullable = false)
    private String titulo;

    @Column(length=13,nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDate lancamento;

    @Column(nullable = false)
    private String sinopse;

    /* */

    @Enumerated(EnumType.STRING)
    private Estoque estoque;

    @Enumerated(EnumType.STRING)
    private Genero genero;



    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    /*tirar dúvida com professor */
    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edicao> edicao = new ArrayList<>();

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacao = new ArrayList<>(); 

    @ManyToOne
    @JoinColumn (name = "id_editora")
    private Editora editora;
    /*tirar dúvida com professor */


    /* */


    /* */

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor; 

    /*@ManyToOne
    @JoinColumn(name = "id_editora")
    private Editora editora */

    /* */

    

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

    public List<Edicao> getEdicao() {
        return edicao;
    }

    public void setEdicao(List<Edicao> edicao) {
        this.edicao = edicao;
    }

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
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

}