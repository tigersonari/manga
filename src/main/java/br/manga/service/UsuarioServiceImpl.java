package br.manga.service;

import java.util.List;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;
import br.manga.model.Usuario;
import br.manga.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senhaHash());
        usuario.setEndereco(dto.endereco());

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senhaHash());
        usuario.setEndereco(dto.endereco());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.listAll().stream()
            .map(UsuarioResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return usuarioRepository.findByNome(nome).stream()
            .map(UsuarioResponseDTO::valueOf)
            .toList();
    }
}