package br.manga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Avaliacao extends DefaultEntity {

    @Min(0) @Max(10)
    @Column(nullable = false)
    private double nota;

    @NotBlank
    @Column(nullable = false)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_manga")
    private Manga manga;

    
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }
}