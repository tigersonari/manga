package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

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
    public AutorResponseDTO create(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setNome(autorDTO.nome());
        autor.setNacionalidade(autorDTO.nacionalidade());
        autorRepository.persist(autor);
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    @Transactional
    public void update(Long id, AutorDTO autorDTO) {
        Autor autor = autorRepository.findById(id);
        autor.setNome(autorDTO.nome());
        autor.setNacionalidade(autorDTO.nacionalidade());
        autorRepository.persist(autor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Autor autor = autorRepository.findById(id);
        autorRepository.delete(autor);
    }

    @Override
    public AutorResponseDTO findById(Long id) {
        Autor autor = autorRepository.findById(id);
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    public AutorResponseDTO findByNome(String nome) {
        Autor autor = autorRepository.findByNome(nome);
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    public List<AutorResponseDTO> findByNacionalidade(String nacionalidade) {
        return autorRepository.findByNacionalidade(nacionalidade).stream()
                .map(AutorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<AutorResponseDTO> findAll() {
        return autorRepository.listAll().stream()
                .map(AutorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
