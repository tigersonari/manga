package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Classificacao {
    LIVRE(0, "Livre"), 
    DEZ_ANOS(1, "10 Anos"),
    DOZE_ANOS(2, "12 Anos"),
    QUATORZE_ANOS(3, "14 Anos"),
    DEZESSEIS_ANOS(4, "16 Anos"),
    DEZOITO_ANOS(5, "18 Anos"),; 

    private final int id; 
    private final String nome;

    Classificacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static Classificacao valueOf(Integer id) {
        if (id == null) return null;
        for (Classificacao c : Classificacao.values()) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}