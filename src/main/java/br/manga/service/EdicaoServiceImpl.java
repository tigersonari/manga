package br.manga.service;

import java.util.List;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.model.Edicao;
import br.manga.model.Formato;
import br.manga.model.Status;
import br.manga.model.TipoCapa;
import br.manga.repository.EdicaoRepository;
import br.manga.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository edicaoRepository;

    @Inject
    MangaRepository mangaRepository;

    @Override
    @Transactional
    public EdicaoResponseDTO create(EdicaoDTO dto) {
        Edicao edicao = new Edicao();
        edicao.setVolume(dto.volume());
        edicao.setIdioma(dto.idioma());
        edicao.setLancamento(dto.lancamento());
        edicao.setDimensao(dto.dimensao());
        edicao.setTitulo(dto.titulo());
        edicao.setFormato(Formato.fromId(dto.formatoId()));
        edicao.setTipoCapa(TipoCapa.fromId(dto.tipoCapaId()));
        edicao.setStatus(Status.valueOf(dto.statusId().toString()));

        
        if (dto.mangaId() != null) {
            edicao.setManga(mangaRepository.findById(dto.mangaId().longValue()));
        }

        edicaoRepository.persist(edicao);
        return EdicaoResponseDTO.valueOf(edicao);
    }

    @Override
    @Transactional
    public void update(Long id, EdicaoDTO dto) {
        Edicao edicao = edicaoRepository.findById(id);
        edicao.setVolume(dto.volume());
        edicao.setIdioma(dto.idioma());
        edicao.setLancamento(dto.lancamento());
        edicao.setDimensao(dto.dimensao());
        edicao.setTitulo(dto.titulo());
        edicao.setFormato(Formato.fromId(dto.formatoId()));
        edicao.setTipoCapa(TipoCapa.fromId(dto.tipoCapaId()));
        edicao.setStatus(Status.valueOf(dto.statusId().toString()));

        if (dto.mangaId() != null) {
            edicao.setManga(mangaRepository.findById(dto.mangaId().longValue()));
        } else {
            edicao.setManga(null); 
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        edicaoRepository.deleteById(id);
    }

    @Override
    public EdicaoResponseDTO findById(Long id) {
        return EdicaoResponseDTO.valueOf(edicaoRepository.findById(id));
    }

    @Override
    public List<EdicaoResponseDTO> findByManga(Long mangaId) {
        return edicaoRepository.findByManga(mangaId).stream()
            .map(EdicaoResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<EdicaoResponseDTO> findAll() {
        return edicaoRepository.listAll().stream()
            .map(EdicaoResponseDTO::valueOf)
            .toList();
    }
}