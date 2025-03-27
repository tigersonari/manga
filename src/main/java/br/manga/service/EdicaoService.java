package br.manga.service;

import java.time.LocalDate;
import java.util.List;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;

public interface EdicaoService {
    EdicaoResponseDTO create(EdicaoDTO edicao);
    void update(Long id, EdicaoDTO edicao);
    void delete(Long id);
    EdicaoResponseDTO findById(Long id);
    List<EdicaoResponseDTO> findByVolume(Integer volume);
    List<EdicaoResponseDTO> findByMangaId(Long idManga);
    List<EdicaoResponseDTO> findByLancamento(LocalDate lancamento);
    List<EdicaoResponseDTO> findByIdioma(String idioma);
    List<EdicaoResponseDTO> findAll();
}
