package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Classificacao {
    LIVRE(1), 
    DEZ_ANOS(2),
    DOZE_ANOS(3),
    QUATORZE_ANOS(4),
    DEZESSEIS_ANOS(5),
    DEZOITO_ANOS(6); 

    private final int ID; 

    Classificacao(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }
}