package br.manga.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.model.Edicao;
import br.manga.repository.EdicaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository edicaoRepository;

    @Override
    @Transactional
    public EdicaoResponseDTO create(EdicaoDTO edicaoDTO) {
        Edicao edicao = new Edicao();
        
        edicao.setVolume(edicaoDTO.volume());
        edicao.setLancamento(edicaoDTO.lancamento());
        edicao.setIdioma(edicaoDTO.idioma());

        edicaoRepository.persist(edicao);
        return EdicaoResponseDTO.valueOf(edicao); 
    }

    @Override
    @Transactional
    public void update(Long id, EdicaoDTO edicaoDTO) {
        Edicao edicao = edicaoRepository.findById(id);

        edicao.setVolume(edicaoDTO.volume());
        edicao.setLancamento(edicaoDTO.lancamento());
        edicao.setIdioma(edicaoDTO.idioma());

        edicaoRepository.persist(edicao);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Edicao edicao = edicaoRepository.findById(id);
        

        edicaoRepository.delete(edicao);  
    }

    @Override
    public EdicaoResponseDTO findById(Long id) {
        Edicao edicao = edicaoRepository.findById(id);
        
        return EdicaoResponseDTO.valueOf(edicao);  
    }

    @Override
    public List<EdicaoResponseDTO> findByVolume(Integer volume) {
        List<Edicao> edicoes = edicaoRepository.find("SELECT e FROM Edicao e WHERE e.volume = ?1", volume).list();
        return edicoes.stream()
                .map(EdicaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EdicaoResponseDTO> findByMangaId(Long idManga) {
        List<Edicao> edicoes = edicaoRepository.find("SELECT e FROM Edicao e WHERE e.manga.id = ?1", idManga).list();
        return edicoes.stream()
                .map(EdicaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EdicaoResponseDTO> findByLancamento(LocalDate lancamento) {
        List<Edicao> edicoes = edicaoRepository.find("SELECT e FROM Edicao e WHERE e.lancamento = ?1", lancamento).list();
        return edicoes.stream()
                .map(EdicaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EdicaoResponseDTO> findByIdioma(String idioma) {
        List<Edicao> edicoes = edicaoRepository.find("SELECT e FROM Edicao e WHERE e.idioma = ?1", idioma).list();
        return edicoes.stream()
                .map(EdicaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EdicaoResponseDTO> findAll() {
        List<Edicao> edicoes = edicaoRepository.findAll().list();
        return edicoes.stream()
                .map(EdicaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
