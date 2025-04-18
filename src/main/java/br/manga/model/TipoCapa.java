package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCapa {
    DURA(1), 
    MOLE(2);

    private final int ID;

    TipoCapa(int ID) {
        this.ID = ID;
    }

    public static TipoCapa fromId(Integer id) {
        if (id == null) return null;
        for (TipoCapa t : values()) {
            if (t.ID == id) return t;
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

    public int getID() {
        return ID;
    }
}