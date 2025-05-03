package br.manga.service;

import java.util.List;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.model.Avaliacao;
import br.manga.model.Manga;
import br.manga.repository.AvaliacaoRepository;
import br.manga.repository.MangaRepository;
import br.manga.repository.UsuarioRepository;
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

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public AvaliacaoResponseDTO create(AvaliacaoDTO dto) {
        Manga manga = mangaRepository.findByIdOptional(dto.mangaId())
                .orElseThrow(() -> new NotFoundException("Mangá não encontrado"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setManga(manga);
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        repository.persist(avaliacao);
        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    @Transactional
    public void update(Long id, AvaliacaoDTO dto) {
        Avaliacao avaliacao = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Avaliação não encontrada"));
        Manga manga = mangaRepository.findByIdOptional(dto.mangaId())
                .orElseThrow(() -> new NotFoundException("Mangá não encontrado"));

        avaliacao.setManga(manga);
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
        List<Avaliacao> avaliacoes = repository.find("manga.id", mangaId).list();
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        return avaliacoes.stream()
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<AvaliacaoResponseDTO> findAll() {
        return repository.listAll()
                .stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .toList();
    }
}