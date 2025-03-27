package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Estoque {
    
    DISPONIVEL(1), 
    INDISPONIVEL(2);

    private int ID;

    Estoque(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public static Estoque valueOf(Integer id) {
        if (id == null)
            return null;
        for (Estoque e : Estoque.values()) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }
    

}
