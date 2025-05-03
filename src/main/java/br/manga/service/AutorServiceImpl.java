package br.manga.service;

import java.util.List;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;
import br.manga.model.Autor;
import br.manga.repository.AutorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AutorServiceImpl implements AutorService {

    @Inject
    AutorRepository repository;

    @Override
    @Transactional
    public AutorResponseDTO create(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        repository.persist(autor);
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    @Transactional
    public void update(Long id, AutorDTO dto) {
        Autor autor = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado"));
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Autor não encontrado");
        }
    }

    @Override
    public AutorResponseDTO findById(Long id) {
        return AutorResponseDTO.valueOf(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Autor não encontrado"))
        );
    }

    @Override
    public List<AutorResponseDTO> findByNome(String nome) {
        return repository.find("nome like ?1", "%" + nome + "%")
                .stream()
                .map(AutorResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<AutorResponseDTO> findByNacionalidade(String nacionalidade) {
        return repository.find("nacionalidade like ?1", "%" + nacionalidade + "%")
                .stream()
                .map(AutorResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<AutorResponseDTO> findAll() {
        return repository.listAll()
                .stream()
                .map(AutorResponseDTO::valueOf)
                .toList();
    }
}