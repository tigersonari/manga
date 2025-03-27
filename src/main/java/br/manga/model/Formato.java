package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Formato {
    
    FISICO(1), 
    DIGITAL(2);

    private int ID;

    Formato(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public static Formato valueOf(Integer id) {
        if (id == null)
            return null;
        for (Formato f : Formato.values()) {
            if (f.getId() == id)
                return f;
        }
        return null;
    }
    

}
