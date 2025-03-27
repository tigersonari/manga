package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    
    FINALIZADO(1), 
    EM_ANDAMENTO(2);

    private int ID;

    Status(int ID) {
        this.ID = ID;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
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
