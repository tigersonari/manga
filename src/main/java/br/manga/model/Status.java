package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    FINALIZADO(1), 
    EM_ANDAMENTO(2);

    private final int ID;

    Status(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }
}