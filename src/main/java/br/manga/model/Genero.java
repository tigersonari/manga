package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genero {
    SHOUNEN(1, "Shounen"),
    SHOUJO(2, "Shoujo"),
    SEINEN(3, "Seinen"),
    JOSEI(4, "Josei"),
    ISEKAI(5, "Isekai"),
    AVENTURA(6, "Aventura"),
    ACAO(7, "Ação"),
    COMEDIA(8, "Comédia"),
    DRAMA(9, "Drama"),
    FANTASIA(10, "Fantasia"),
    MISTERIO(11, "Mistério"),
    ROMANCE(12, "Romance"),
    MARVEL(13, "Marvel"),
    DC(14, "DC"),
    HEROI(15, "Herói"),;

    private int id;
    private final String nome;

    Genero(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public static Genero valueOf(Integer id) {
        if (id == null)
            return null;
        for (Genero g : Genero.values()) {
            if (g.getId().equals(id))
                return g;
        }
        return null;
    }

}