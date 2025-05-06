package br.manga.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminDTO(

    @NotBlank(message = "nome é obrigatório")
    @Size(max = 100, message = "nome deve ser inferior a 100 caracteres")
    String nome,
    
    @NotBlank(message = "email vazio")
    @Email(message = "email inválido")
    String email,
    
    @NotBlank(message = "senha é obrigatória")
    @Size(min = 10, message = "a senha deve ter pelo menos 10 caracteres")
    String senha,
    
    String endereco,
    
    @NotBlank(message = "permissão não pode ser vazia")
    String permissao
) {}