package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genero {
    SHOUNEN(1),
    SHOUJO(2),
    SEINEN(3),
    JOSEI(4),
    ISEKAI(5);

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