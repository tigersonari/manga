package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Estoque {
    DISPONIVEL(1), 
    INDISPONIVEL(2);

    private final int ID; // Campo final

    Estoque(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }
}