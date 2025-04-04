package br.manga.dto;

import br.manga.model.Usuario;

public record UsuarioResponseDTO(
    String nome,
    String email,
    String senha,
    String endereco
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getEndereco());
    }
}


