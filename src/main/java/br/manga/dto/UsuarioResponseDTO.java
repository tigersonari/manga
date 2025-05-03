package br.manga.dto;

import br.manga.model.Admin;
import br.manga.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    String endereco,
    String tipo
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getEndereco(),
            "USER"
        );
    }

    public static UsuarioResponseDTO valueOf(Admin admin) {
        if (admin == null) return null;
        return new UsuarioResponseDTO(
            admin.getId(),
            admin.getNome(),
            admin.getEmail(),
            admin.getEndereco(),
            "ADMIN"
        );
    }
}