package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Classificacao {
    LIVRE(1, "Livre"), 
    DEZ_ANOS(2, "10 Anos"),
    DOZE_ANOS(3, "12 Anos"),
    QUATORZE_ANOS(4, "14 Anos"),
    DEZESSEIS_ANOS(5, "16 Anos"),
    DEZOITO_ANOS(6, "18 Anos"),; 

    private final int id; 
    private final String nome;

    Classificacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
}