package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genero {
    SHOUNEN(1),
    SHOUJO(2),
    SEINEN(3),
    JOSEI(4),
    ISEKAI(5),
    AVENTURA(6),
    ACAO(7),
    COMEDIA(8),
    DRAMA(9),
    FANTASIA(10),
    MISTERIO(11),
    ROMANCE(12),
    MARVEL(13),
    DC(14),
    HEROI(15);

    private int ID;

    Genero(int ID) {
        this.ID = ID;
    }

    public Integer getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public static Genero valueOf(Integer id) {
        if (id == null)
            return null;
        for (Genero g : Genero.values()) {
            if (g.getId() == id)
                return g;
        }
        return null;
    }

}