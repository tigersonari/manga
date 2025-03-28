package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.model.Avaliacao;
import br.manga.repository.AvaliacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Inject
    AvaliacaoRepository avaliacaoRepository;

    @Override
    @Transactional
    public AvaliacaoResponseDTO create(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = new Avaliacao();
        
        avaliacao.setNota(avaliacaoDTO.nota());
        avaliacao.setComentario(avaliacaoDTO.comentario());
        avaliacaoRepository.persist(avaliacao);
        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    @Transactional
    public void update(Long id, AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id);
            avaliacao.setNota(avaliacaoDTO.nota());
            avaliacao.setComentario(avaliacaoDTO.comentario());
            avaliacaoRepository.persist(avaliacao);
        
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id);
        
            avaliacaoRepository.delete(avaliacao);
        
    }

    @Override
    public AvaliacaoResponseDTO findById(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id);
        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }
    
    /*@Override
    public List<AvaliacaoResponseDTO> findByMangaId(Long idManga) {
        List<Avaliacao> avaliacaoList = repository.findByMangaId(idManga);
        return avaliacaoList.stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<AvaliacaoResponseDTO> findAll() {
        return avaliacaoRepository.listAll().stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
