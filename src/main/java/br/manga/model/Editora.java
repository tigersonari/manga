package br.manga.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum Editora {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    
    //conjunto de constantes, não é alterável
    KODANSHA(1, "Kodansha"), 
    SHUEISHA(2,"Shueisha");

    private final int ID; //deve ser maiusculo por ser uma constante
    private final String NOME; //ps:e pesquisae sobre o final

    //ps: o enumerador é fixo e não pode possuir o estado 

    //construtor para alterar os valores das constantes, elas só poderão ser alteradas se houver esse construtor
    //todo construtor é PRIVATE
    
    private Editora(int id, String nome){
        this.ID = id;
        this.NOME = nome;
    }

    public int getId(){
        return ID;
    }

    public String getNome(){
        return NOME;
    }

    public static Editora valueOf(Integer id){

           if (id == null)
               return null;

            for (Editora r : Editora.values()){
                if (r.getId() == id)
                    return r;
            }
            return null;

    }

}