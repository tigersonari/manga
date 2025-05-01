package br.manga.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "edicao", uniqueConstraints = @UniqueConstraint(columnNames = {"titulo", "volume"}))
public class Edicao extends DefaultEntity{

    
    @Positive
    @Column(nullable = false)
    private Integer volume;

    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false)
    private LocalDate lancamento;

    @Column(nullable = false)
    private String dimensao;


    /*ultima alteração */
    @Column(nullable = false)
    private String titulo;
    /* */

 
    @Column(nullable = false)
    private Formato formato;

    @Column(nullable = false)
    private TipoCapa tipoCapa;

    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_manga")
    private Manga manga;



    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public TipoCapa getTipoCapa() {
        return tipoCapa;
    }

    public void setTipoCapa(TipoCapa tipoCapa) {
        this.tipoCapa = tipoCapa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    

}
