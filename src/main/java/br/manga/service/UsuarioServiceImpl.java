package br.manga.service;

import java.util.List;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;
import br.manga.model.Usuario;
import br.manga.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(UsuarioDTO dto) {
        
        if (repository.find("email", dto.email()).count() > 0) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senha()); 
        usuario.setEndereco(dto.endereco());
        
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com id: " + id));
        
        if (!usuario.getEmail().equals(dto.email())) {
            if (repository.find("email = ?1 and id != ?2", dto.email(), id).count() > 0) {
                throw new IllegalArgumentException("Email já está em uso por outro usuário");
            }
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        
        
        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenhaHash(dto.senha());
        }
        
        usuario.setEndereco(dto.endereco());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Usuário não encontrado com id: " + id);
        }
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com id: " + id))
        );
    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        return UsuarioResponseDTO.valueOf(
            repository.find("email", email)
                .firstResultOptional()
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com email: " + email))
        );
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return repository.find("nome like ?1", "%" + nome + "%")
                .stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return repository.listAll()
                .stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }
}