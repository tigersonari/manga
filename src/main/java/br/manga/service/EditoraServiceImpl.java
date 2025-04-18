package br.manga.service;

import java.util.List;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;
import br.manga.model.Editora;
import br.manga.repository.EditoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EditoraServiceImpl implements EditoraService {

    @Inject
    EditoraRepository editoraRepository;

    @Override
    @Transactional
    public EditoraResponseDTO create(EditoraDTO dto) {
        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setSede(dto.sede());
        editora.setFundacao(dto.fundacao());

        editoraRepository.persist(editora);
        return EditoraResponseDTO.valueOf(editora);
    }

    @Override
    @Transactional
    public void update(Long id, EditoraDTO dto) {
        Editora editora = editoraRepository.findById(id);
        editora.setNome(dto.nome());
        editora.setSede(dto.sede());
        editora.setFundacao(dto.fundacao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        editoraRepository.deleteById(id);
    }

    @Override
    public EditoraResponseDTO findById(Long id) {
        return EditoraResponseDTO.valueOf(editoraRepository.findById(id));
    }

    @Override
    public List<EditoraResponseDTO> findByNome(String nome) {
        return editoraRepository.findByNome(nome).stream()
            .map(EditoraResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EditoraResponseDTO> findAll() {
        return editoraRepository.listAll().stream()
            .map(EditoraResponseDTO::valueOf)
            .toList();
    }
}