package br.manga.service;

import java.util.List;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.model.Avaliacao;
import br.manga.model.Manga;
import br.manga.repository.AvaliacaoRepository;
import br.manga.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Inject
    AvaliacaoRepository repository;

    @Inject
    MangaRepository mangaRepository;

    @Override
    @Transactional
    public AvaliacaoResponseDTO create(AvaliacaoDTO dto) {
        Manga manga = mangaRepository.findByIdOptional(dto.mangaId())
            .orElseThrow(() -> new NotFoundException("Mangá não encontrado"));
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setManga(manga);
        
        repository.persist(avaliacao);
        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    @Transactional
    public void update(Long id, AvaliacaoDTO dto) {
        Avaliacao avaliacao = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Avaliação não encontrada"));
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Avaliação não encontrada");
        }
    }

    @Override
    public AvaliacaoResponseDTO findById(Long id) {
        return AvaliacaoResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Avaliação não encontrada"))
        );
    }

    @Override
    public List<AvaliacaoResponseDTO> findByManga(Long mangaId) {
        return repository.find("manga.id", mangaId)
            .stream()
            .map(AvaliacaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<AvaliacaoResponseDTO> findByNotaGreaterThanEqual(Double nota) {
        return repository.find("nota >= ?1", nota)
            .stream()
            .map(AvaliacaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public Double calcularMediaNotas(Long mangaId) {
        return repository.find("select avg(nota) from Avaliacao where manga.id = ?1", mangaId)
            .project(Double.class)
            .firstResult();
    }

    @Override
    public List<AvaliacaoResponseDTO> findAll() {
        return repository.listAll()
            .stream()
            .map(AvaliacaoResponseDTO::valueOf)
            .toList();
    }
}