package br.manga.dto;

import br.manga.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    String endereco,
    String tipoUsuario
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getEndereco(),
            usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().name() : null
        );
    }
}