package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Formato {
    FISICO(1, "Físico"), 
    DIGITAL(2, "Digital"),;

    private final int id;
    private final String nome;

    Formato(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
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