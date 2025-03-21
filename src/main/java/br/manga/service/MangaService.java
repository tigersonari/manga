package br.manga.service;


import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;

public interface MangaService {
    
  public MangaResponseDTO create(MangaDTO manga);
  void update(MangaDTO manga, Long id);
  void delete(Long id);
  public MangaResponseDTO findById(Long id);
  public MangaResponseDTO findBySigla(String sigla);
  public List<MangaResponseDTO> findAll();

}