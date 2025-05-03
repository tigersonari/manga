package br.manga.service;

import java.util.List;

import br.manga.dto.AdminDTO;
import br.manga.dto.AdminResponseDTO;
import br.manga.model.Admin;
import br.manga.repository.AdminRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AdminServiceImpl implements AdminService {

    @Inject
    AdminRepository repository;

    @Override
    @Transactional
    public AdminResponseDTO create(AdminDTO dto) {
        
        if (repository.find("email", dto.email()).count() > 0) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Admin admin = new Admin();
        admin.setNome(dto.nome());
        admin.setEmail(dto.email());
        admin.setSenhaHash(dto.senha());  // senha em texto puro
        admin.setEndereco(dto.endereco());
        admin.setPermissao(dto.permissao());
        
        repository.persist(admin);
        return AdminResponseDTO.valueOf(admin);
    }

    @Override
    @Transactional
    public void update(Long id, AdminDTO dto) {
        Admin admin = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Admin não encontrado com id: " + id));
        
        
        if (!admin.getEmail().equals(dto.email())) {
            if (repository.find("email = ?1 and id != ?2", dto.email(), id).count() > 0) {
                throw new IllegalArgumentException("Email já está em uso por outro admin");
            }
        }

        admin.setNome(dto.nome());
        admin.setEmail(dto.email());
        
     
        if (dto.senha() != null && !dto.senha().isBlank()) {
            admin.setSenhaHash(dto.senha());
        }
        
        admin.setEndereco(dto.endereco());
        admin.setPermissao(dto.permissao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Admin não encontrado com id: " + id);
        }
    }

    @Override
    public AdminResponseDTO findById(Long id) {
        return AdminResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Admin não encontrado com id: " + id))
        );
    }

    @Override
    public List<AdminResponseDTO> findByPermissao(String permissao) {
        return repository.find("permissao", permissao)
                .stream()
                .map(AdminResponseDTO::valueOf)
                .toList();
    }

}