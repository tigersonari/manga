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

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static TipoCapa fromId(Integer id) {
        for (TipoCapa tipoCapa : values()) {
            if (tipoCapa.ordinal() == id) {
                return tipoCapa;
            }
        }
        throw new IllegalArgumentException("Invalid TipoCapa ID: " + id);
    }
}