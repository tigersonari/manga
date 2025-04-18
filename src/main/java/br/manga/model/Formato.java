package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Formato {
    FISICO(1), 
    DIGITAL(2);

    private final int ID;

    Formato(int ID) {
        this.ID = ID;
    }

    public static Formato fromId(Integer id) { 
        if (id == null) return null;
        for (Formato f : values()) {
            if (f.ID == id) return f;
        }
        throw new IllegalArgumentException("ID inválido: " + id); 
    }

    public int getID() {
        return ID;
    }

    
}