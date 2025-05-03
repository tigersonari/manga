package br.manga.service;

import java.util.List;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.model.Edicao;
import br.manga.model.Formato;
import br.manga.model.Manga;
import br.manga.model.Status;
import br.manga.model.TipoCapa;
import br.manga.repository.EdicaoRepository;
import br.manga.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository repository;

    @Inject
    MangaRepository mangaRepository;

    @Override
    @Transactional
    public EdicaoResponseDTO create(EdicaoDTO dto) {
        Manga manga = mangaRepository.findByIdOptional(dto.mangaId())
            .orElseThrow(() -> new NotFoundException("Mangá não encontrado"));

        try {
            Formato formato = Formato.valueOf(dto.formatoId());
            TipoCapa tipoCapa = TipoCapa.fromId(dto.tipoCapaId());
            Status status = Status.valueOf(dto.statusId());

            Edicao edicao = new Edicao();
            edicao.setVolume(dto.volume());
            edicao.setIdioma(dto.idioma());
            edicao.setLancamento(dto.lancamento());
            edicao.setDimensao(dto.dimensao());
            edicao.setTitulo(dto.titulo());
            edicao.setFormato(formato);
            edicao.setTipoCapa(tipoCapa);
            edicao.setStatus(status);
            edicao.setManga(manga);

            repository.persist(edicao);
            return EdicaoResponseDTO.valueOf(edicao);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("IDs inválidos para formato, tipoCapa ou status", 400);
        }
    }

    @Override
    @Transactional
    public void update(Long id, EdicaoDTO dto) {
        Edicao edicao = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Edição não encontrada"));

        try {
            edicao.setVolume(dto.volume());
            edicao.setIdioma(dto.idioma());
            edicao.setLancamento(dto.lancamento());
            edicao.setDimensao(dto.dimensao());
            edicao.setTitulo(dto.titulo());
            edicao.setFormato(Formato.valueOf(dto.formatoId()));
            edicao.setTipoCapa(TipoCapa.fromId(dto.tipoCapaId()));
            edicao.setStatus(Status.valueOf(dto.statusId()));
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("IDs inválidos para formato, tipoCapa ou status", 400);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Edição não encontrada");
        }
    }

    @Override
    public EdicaoResponseDTO findById(Long id) {
        return EdicaoResponseDTO.valueOf(
            repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Edição não encontrada"))
        );
    }

    @Override
    public List<EdicaoResponseDTO> findByManga(Long mangaId) {
        return repository.find("manga.id", mangaId)
            .stream()
            .map(EdicaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EdicaoResponseDTO> findByFormato(Integer formatoId) {
        return repository.find("formato.id", formatoId)
            .stream()
            .map(EdicaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EdicaoResponseDTO> findByStatus(Integer statusId) {
        return repository.find("status.id", statusId)
            .stream()
            .map(EdicaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public EdicaoResponseDTO findByVolumeAndManga(Integer volume, Long mangaId) {
        return EdicaoResponseDTO.valueOf(
            repository.find("volume = ?1 and manga.id = ?2", volume, mangaId).firstResult()
        );
    }

    @Override
    public List<EdicaoResponseDTO> findAll() {
        return repository.listAll()
            .stream()
            .map(EdicaoResponseDTO::valueOf)
            .toList();
    }
}