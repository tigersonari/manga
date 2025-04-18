package br.manga.service;

import java.util.List;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.model.Avaliacao;
import br.manga.repository.AvaliacaoRepository;
import br.manga.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Inject
    AvaliacaoRepository avaliacaoRepository;

    @Inject
    MangaRepository mangaRepository;

    @Override
    @Transactional
    public AvaliacaoResponseDTO create(AvaliacaoDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setManga(mangaRepository.findById(dto.mangaId()));

        avaliacaoRepository.persist(avaliacao);
        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    @Transactional
    public void update(Long id, AvaliacaoDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id);
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setManga(mangaRepository.findById(dto.mangaId()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    @Override
    public AvaliacaoResponseDTO findById(Long id) {
        return AvaliacaoResponseDTO.valueOf(avaliacaoRepository.findById(id));
    }

    @Override
    public List<AvaliacaoResponseDTO> findByManga(Long idManga) {
        return avaliacaoRepository.findByManga(idManga).stream()
            .map(AvaliacaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<AvaliacaoResponseDTO> findAll() {
        return avaliacaoRepository.listAll().stream()
            .map(AvaliacaoResponseDTO::valueOf)
            .toList();
    }
}