package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;
import br.manga.model.TipoUsuario;
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
        usuario.setSenhaHash(dto.senha()); // Em produção, use BCrypt!
        usuario.setEndereco(dto.endereco());
        usuario.setTipoUsuario(TipoUsuario.valueOf(dto.idTipoUsuario().toString()));

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senha());
        usuario.setEndereco(dto.endereco());
        usuario.setTipoUsuario(TipoUsuario.valueOf(dto.idTipoUsuario().toString()));
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
    public List<UsuarioResponseDTO> findByNome(String nome) {
        List<Usuario> usuarios = List.of(usuarioRepository.findByNome(nome));
        return usuarios.stream()
            .map(usuario -> UsuarioResponseDTO.valueOf(usuario))
            .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.listAll().stream()
            .map(UsuarioResponseDTO::valueOf)
            .toList();
    }
}