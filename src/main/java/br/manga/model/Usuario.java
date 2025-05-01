package br.manga.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("USER")
public class Usuario extends UsuarioBase {
    // 
}