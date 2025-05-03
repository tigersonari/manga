package br.manga.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
    @NotBlank(message = "usuario necessita de nome")
    @Size(max = 100, message = "nome deve ser inferior a 100 caracteres")
    String nome,
    
    @NotBlank(message = "email vazios não são aceitos")
    @Email(message = "email inválido")
    String email,
    
    @NotBlank(message = "senhz é obrigatória")
    @Size(min = 10, message = "a senha deve ter pelo menos 10 caracteres")
    String senha,
    
    String endereco
) {}