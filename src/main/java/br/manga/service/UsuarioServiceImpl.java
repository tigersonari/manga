package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

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
    public UsuarioResponseDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setEndereco(usuarioDTO.endereco());
        
        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setEndereco(usuarioDTO.endereco());

        usuarioRepository.persist(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findByEmail(email));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .stream().map(UsuarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }


    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAllUsuarios()
                .stream().map(UsuarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
