package br.manga.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Manga extends PanacheEntityBase {

    @Column(length = 60,nullable = false)
    private String titulo;

    @Column(length = 2,nullable = false)
    private String sigla;

    @Column(length = 2,nullable = false)
    private Integer volume;

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    
    private Editora editora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla){
        this.sigla = sigla;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora){
        this.editora = editora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }



    

    /*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer volume;
    private String autor;
    private String editora; 

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume){
        this.volume = volume;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
*/
}