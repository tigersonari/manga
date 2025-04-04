package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.model.Autor;
import br.manga.model.Avaliacao;
import br.manga.model.Classificacao;
import br.manga.model.Edicao;
import br.manga.model.Editora;
import br.manga.model.Estoque;
import br.manga.model.Genero;
import br.manga.model.Manga;
import br.manga.repository.AutorRepository;
import br.manga.repository.AvaliacaoRepository;
import br.manga.repository.EdicaoRepository;
import br.manga.repository.EditoraRepository;
import br.manga.repository.MangaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MangaServiceImpl implements MangaService {

    @Inject
    MangaRepository mangaRepository;
    @Inject
    AutorRepository autorRepository;
    @Inject
    AvaliacaoRepository avaliacaoRepository;
    @Inject
    EdicaoRepository edicaoRepository;
    @Inject
    EditoraRepository editoraRepository;

    @Override
    @Transactional
    public MangaResponseDTO create(MangaDTO manga) {
        Manga novoManga = new Manga();


        novoManga.setTitulo(manga.titulo());
        novoManga.setIsbn(manga.isbn()); /*tirar dúvida com professor */
        novoManga.setLancamento(manga.lancamento());
        novoManga.setSinopse(manga.sinopse());

        novoManga.setEstoque(Estoque.valueOf(manga.idEstoque()));
        novoManga.setGenero(Genero.valueOf(manga.idGenero()));
        novoManga.setClassificacao(Classificacao.valueOf(manga.idClassificacao()));
        
        Edicao edicao = edicaoRepository.findById(Long.valueOf(manga.idEdicao()));
        if (edicao == null) {
            throw new RuntimeException("Edição não encontrada"); /*tiar dúvida com professor sobre erro anterior */
        }
        novoManga.setEdicao(List.of(edicao));

        Avaliacao avaliacao = avaliacaoRepository.findById(Long.valueOf(manga.idAvaliacao()));
        if (avaliacao == null) {
            throw new RuntimeException("Avaliacao não encontrada");
        }
        novoManga.setAvaliacao(List.of(avaliacao));

        Editora editora = editoraRepository.findById(Long.valueOf(manga.idEditora()));
        if (editora == null) {
            throw new RuntimeException("Editora não encontrada");
        }
        novoManga.setEditora(editora);

        Autor autor = autorRepository.findById(Long.valueOf(manga.idAutor()));
        if (autor == null) {
            throw new RuntimeException("Editora não encontrada");
        }
        novoManga.setAutor(autor);


       mangaRepository.persist(novoManga);
        return MangaResponseDTO.valueOf(novoManga);
    }

    @Override
    @Transactional
    public void update(Long id, MangaDTO mangaDTO) {
        Manga manga = mangaRepository.findById(id);

    
    manga.setTitulo(mangaDTO.titulo());
    manga.setIsbn(mangaDTO.isbn());
    manga.setLancamento(mangaDTO.lancamento());
    manga.setSinopse(mangaDTO.sinopse());

    
    Estoque estoque = Estoque.valueOf(mangaDTO.idEstoque().toString());
    manga.setEstoque(estoque);

    Genero genero = Genero.valueOf(mangaDTO.idGenero().toString());
    manga.setGenero(genero);

    Classificacao classificacao = Classificacao.valueOf(mangaDTO.idClassificacao().toString());
    manga.setClassificacao(classificacao);

    
    Edicao edicao = edicaoRepository.findById(Long.valueOf(mangaDTO.idEdicao()));
    
    manga.setEdicao(List.of(edicao));  

    
    Avaliacao avaliacao = avaliacaoRepository.findById(Long.valueOf(mangaDTO.idAvaliacao()));
    
    manga.setAvaliacao(List.of(avaliacao));  

  
    Editora editora = editoraRepository.findById(Long.valueOf(mangaDTO.idEditora()));
    
    manga.setEditora(editora);

    
    Autor autor = autorRepository.findById(Long.valueOf(mangaDTO.idAutor()));
    
    manga.setAutor(autor);

  
    mangaRepository.persist(manga);
}

@Override
@Transactional
public void delete(Long id) {
    Manga manga = mangaRepository.findById(id);
}

@Override
public MangaResponseDTO findById(Long id) {
    Manga manga = mangaRepository.findById(id);
    
    return MangaResponseDTO.valueOf(manga);
}

@Override
public MangaResponseDTO findByTitulo(String titulo) {
    Manga manga = mangaRepository.findByTitulo(titulo);
    

    return MangaResponseDTO.valueOf(manga);
}

@Override
public List<MangaResponseDTO> findByAutor(String autor) {
    List<Manga> mangas = mangaRepository.findMangasByAutor(autor);
    return mangas.stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
}

@Override
public List<MangaResponseDTO> findByEditora(String editora) {
    List<Manga> mangas = mangaRepository.findMangaByEditora(editora);
    return mangas.stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
}

@Override
public List<MangaResponseDTO> findByGenero(String genero) {
    List<Manga> mangas = mangaRepository.findByGenero(genero);
    return mangas.stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
}

@Override
public List<MangaResponseDTO> findAll() {
    List<Manga> mangas = mangaRepository.findAllMangas();
    return mangas.stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
}


}
