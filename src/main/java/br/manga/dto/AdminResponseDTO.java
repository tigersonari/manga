package br.manga.dto;

import br.manga.model.Admin;

public record AdminResponseDTO(
    Long id,
    String nome,
    String email,
    String endereco,
    String permissao
) {
    public static AdminResponseDTO valueOf(Admin admin) {
        if (admin == null) return null;
        return new AdminResponseDTO(
            admin.getId(),
            admin.getNome(),
            admin.getEmail(),
            admin.getEndereco(),
            admin.getPermissao()
        );
    }
}