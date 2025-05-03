package br.manga.service;

import java.util.List;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;
import br.manga.model.Editora;
import br.manga.repository.EditoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EditoraServiceImpl implements EditoraService {

    @Inject
    EditoraRepository repository;

    @Override
    @Transactional
    public EditoraResponseDTO create(EditoraDTO dto) {
        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setSede(dto.sede());
        editora.setFundacao(dto.fundacao());
        
        repository.persist(editora);
        return EditoraResponseDTO.valueOf(editora);
    }

    @Override
    @Transactional
    public void update(Long id, EditoraDTO dto) {
        Editora editora = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Editora não encontrada"));
        editora.setNome(dto.nome());
        editora.setSede(dto.sede());
        editora.setFundacao(dto.fundacao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Editora não encontrada");
        }
    }

    @Override
    public EditoraResponseDTO findById(Long id) {
        return EditoraResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Editora não encontrada"))
        );
    }

    @Override
    public List<EditoraResponseDTO> findByNome(String nome) {
        return repository.find("nome like ?1", "%" + nome + "%")
            .stream()
            .map(EditoraResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EditoraResponseDTO> findBySede(String sede) {
        return repository.find("sede", sede)
            .stream()
            .map(EditoraResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EditoraResponseDTO> findByAnoFundacao(int ano) {
        return repository.find("year(fundacao) = ?1", ano)
            .stream()
            .map(EditoraResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EditoraResponseDTO> findAll() {
        return repository.listAll()
            .stream()
            .map(EditoraResponseDTO::valueOf)
            .toList();
    }
}