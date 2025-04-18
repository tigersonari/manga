package br.manga.dto;

public record UsuarioDTO(
    String nome,
    String email,
    String senha,
    String endereco,
    Long idTipoUsuario
) {}