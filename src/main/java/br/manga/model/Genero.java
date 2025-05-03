package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genero {
    SHOUNEN(0, "Shounen"),
    SHOUJO(1, "Shoujo"),
    SEINEN(2, "Seinen"),
    JOSEI(3, "Josei"),
    ISEKAI(4, "Isekai"),
    AVENTURA(5, "Aventura"),
    ACAO(6, "Ação"),
    COMEDIA(7, "Comédia"),
    DRAMA(8, "Drama"),
    FANTASIA(9, "Fantasia"),
    MISTERIO(10, "Mistério"),
    ROMANCE(11, "Romance"),
    MARVEL(12, "Marvel"),
    DC(13, "DC"),
    HEROI(14, "Herói"),;

    private final int id;
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
        if (id == null) return null;
        for (Genero g : Genero.values()) {
            if (g.getId() == id) return g;
        }
        return null;
    }

}