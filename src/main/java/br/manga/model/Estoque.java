package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Estoque {
    DISPONIVEL(0, "Disponível"), 
    INDISPONIVEL(1, "Indisponível");

    private final int id;
    private final String nome;

    Estoque(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
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