package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCapa {
    
    DURA(1), 
    MOLE(2);

    private int ID;

    TipoCapa(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public static TipoCapa valueOf(Integer id) {
        if (id == null)
            return null;
        for (TipoCapa t : TipoCapa.values()) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }
    

}

