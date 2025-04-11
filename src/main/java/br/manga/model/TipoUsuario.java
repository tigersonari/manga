package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoUsuario {
    ADMIN,
    USER,
    GUEST;

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

   

}