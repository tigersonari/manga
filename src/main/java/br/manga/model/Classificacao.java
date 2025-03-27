package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Classificacao {
    
    LIVRE(1), 
    DEZ_ANOS(2),
    DOZE_ANOS(3),
    QUATORZE_ANOS(4),
    DEZESSEIS_ANOS(5),
    DEZOITO (6);

    private int ID;

    Classificacao(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public static Classificacao valueOf(Integer id) {
        if (id == null)
            return null;
        for (Classificacao c : Classificacao.values()) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }
    

}

