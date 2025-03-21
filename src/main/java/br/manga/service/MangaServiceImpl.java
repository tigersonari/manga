package br.manga.service;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.model.Editora;
import br.manga.model.Manga;
import br.manga.repository.MangashRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MangaServiceImpl implements MangaService {

    @Inject
    MangashRepository mangashRepository;

    @Override
    @Transactional
    public MangaResponseDTO create(MangaDTO manga) {


        Manga novoManga = new Manga();
            novoManga.setTitulo(manga.titulo());
            novoManga.setSigla(manga.sigla());

            novoManga.setEditora(Editora.valueOf(manga.idEditora()));
            
            mangashRepository.persist(novoManga);

            return MangaResponseDTO.valueOf(novoManga);

    }

    @Override
    @Transactional
    public void update(MangaDTO manga, Long id) {

        Manga edicaoManga = mangashRepository.findById(id);

        edicaoManga.setTitulo(manga.titulo());
        edicaoManga.setSigla(manga.sigla());
        edicaoManga.setEditora(Editora.valueOf(manga.idEditora()));

    }

    @Override
    @Transactional
    public void delete(Long id) {

        mangashRepository.deleteById(id);
        Manga manga = mangashRepository.findById(id);
        mangashRepository.delete(manga);

    }

    @Override
    public MangaResponseDTO findById(Long id) {
        return MangaResponseDTO.valueOf(mangashRepository.findById(id));
    }

    @Override
    public MangaResponseDTO findBySigla(String sigla) {
        return MangaResponseDTO.valueOf(mangashRepository.findBySigla(sigla));
    }

    @Override
    public List<MangaResponseDTO> findAll() {
        return mangashRepository.findAll().stream().map(e -> MangaResponseDTO.valueOf(e)).toList();
    }
    
}