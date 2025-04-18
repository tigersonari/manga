package br.manga.service;

import java.util.List;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;
import br.manga.model.Autor;
import br.manga.repository.AutorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AutorServiceImpl implements AutorService {

    @Inject
    AutorRepository autorRepository;

    @Override
    @Transactional
    public AutorResponseDTO create(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());

        autorRepository.persist(autor);
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    @Transactional
    public void update(Long id, AutorDTO dto) {
        Autor autor = autorRepository.findById(id);
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public AutorResponseDTO findById(Long id) {
        return AutorResponseDTO.valueOf(autorRepository.findById(id));
    }

    @Override
    public List<AutorResponseDTO> findByNome(String nome) {
        return autorRepository.findByNome(nome).stream()
            .map(AutorResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<AutorResponseDTO> findByNacionalidade(String nacionalidade) {
        return autorRepository.findByNacionalidade(nacionalidade).stream()
            .map(AutorResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<AutorResponseDTO> findAll() {
        return autorRepository.listAll().stream()
            .map(AutorResponseDTO::valueOf)
            .toList();
    }
}