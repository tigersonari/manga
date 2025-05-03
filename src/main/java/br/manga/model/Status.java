package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    FINALIZADO(0, "Finalizado"), 
    EM_ANDAMENTO(1, "Em Andamento");

    private final int id;
    private final String nome;

    Status(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public static Status valueOf(Integer id) {
        if (id == null)
            return null;
        for (Status s : Status.values()) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }
}