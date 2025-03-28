package br.manga.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public EditoraResponseDTO create(EditoraDTO editoraDTO) {
        Editora editora = new Editora();
        editora.setNome(editoraDTO.nome());
        editora.setSede(editoraDTO.sede());
        editora.setFundacao(editoraDTO.fundacao());
        editoraRepository.persist(editora);
        return EditoraResponseDTO.valueOf(editora);
    }

    @Override
    @Transactional
    public void update(Long id, EditoraDTO editoraDTO) {
        Editora editora = editoraRepository.findById(id);
        
        editora.setNome(editoraDTO.nome());
        editora.setSede(editoraDTO.sede());
        editora.setFundacao(editoraDTO.fundacao());
        editoraRepository.persist(editora);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Editora editora = editoraRepository.findById(id);
        
        editoraRepository.delete(editora);
    }

    @Override
    public EditoraResponseDTO findById(Long id) {
        Editora editora = editoraRepository.findById(id);
        
        return EditoraResponseDTO.valueOf(editora);
    }

    @Override
    public List<EditoraResponseDTO> findByNome(String nome) {
        List<Editora> editoras = editoraRepository.findByNome(nome);
        
        return editoras.stream()
                .map(EditoraResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EditoraResponseDTO> findBySede(String sede) {
        List<Editora> editoras = editoraRepository.find("SELECT e FROM Editora e WHERE e.sede = ?1", sede).list();
        return editoras.stream()
                .map(EditoraResponseDTO::valueOf)
                .collect(Collectors.toList());
    } /*tirar dúvida com professor sobre os erros anteriores nas consultas em todos os ServiceImpl */

    @Override
    public List<EditoraResponseDTO> findByFundacao(LocalDate fundacao) {
        List<Editora> editoras = editoraRepository.find("SELECT e FROM Editora e WHERE e.fundacao = ?1", fundacao).list();
        return editoras.stream()
                .map(EditoraResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EditoraResponseDTO> findAll() {
        List<Editora> editoras = editoraRepository.findAll().list();
        return editoras.stream()
                .map(EditoraResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
