package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCapa {
    DURA(0, "Dura"), 
    MOLE(1, "Mole"),;

    private final int id;
    private final String nome;

    TipoCapa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static TipoCapa fromId(Integer id) {
        if (id == null) return null;
        for (TipoCapa t : values()) {
            if (t.id == id) return t;
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}