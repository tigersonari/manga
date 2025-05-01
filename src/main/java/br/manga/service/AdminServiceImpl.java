package br.manga.service;

import java.util.List;

import br.manga.dto.AdminDTO;
import br.manga.dto.AdminResponseDTO;
import br.manga.model.Admin;
import br.manga.repository.AdminRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AdminServiceImpl implements AdminService {

    @Inject
    AdminRepository adminRepository;

    @Override
    @Transactional
    public AdminResponseDTO create(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setNome(dto.nome());
        admin.setEmail(dto.email());
        admin.setSenhaHash(dto.senhaHash());
        admin.setEndereco(dto.endereco());
        admin.setPermissao(dto.permissao());

        adminRepository.persist(admin);
        return AdminResponseDTO.valueOf(admin);
    }

    @Override
    @Transactional
    public void update(Long id, AdminDTO dto) {
        Admin admin = adminRepository.findById(id);
        if (admin == null) {
            throw new RuntimeException("Admin não encontrado");
        }

        admin.setNome(dto.nome());
        admin.setEmail(dto.email());
        admin.setSenhaHash(dto.senhaHash());
        admin.setEndereco(dto.endereco());
        admin.setPermissao(dto.permissao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public AdminResponseDTO findById(Long id) {
        return AdminResponseDTO.valueOf(adminRepository.findById(id));
    }

    @Override
    public List<AdminResponseDTO> findAll() {
        return adminRepository.listAll().stream()
            .map(AdminResponseDTO::valueOf)
            .toList();
    }

    @Override
    public AdminResponseDTO findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return null;
        }
        return AdminResponseDTO.valueOf(adminRepository.findByNome(nome));
    }
}