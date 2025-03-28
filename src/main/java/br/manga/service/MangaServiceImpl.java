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
import br.manga.repository.MangaRepository;
import br.manga.service.MangaService;
import br.manga.repository.AutorRepository;
import br.manga.repository.AvaliacaoRepository;
import br.manga.repository.EdicaoRepository;
import br.manga.repository.EditoraRepository;
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

        /*novoManga.setTitulo(manga.titulo());
        novoManga.setAutor(manga.autor());
        novoManga.setEditora(manga.editora());
        novoManga.setGenero(manga.genero());
        novoManga.setSinopse(manga.sinopse());
        novoManga.setAnoLancamento(manga.anoLancamento());*/

        novoManga.setTitulo(manga.titulo());
        novoManga.setIsbn(manga.isbn()); /*tirar dúvida com professor */
        novoManga.setLancamento(manga.lancamento());
        novoManga.setSinopse(manga.sinopse());

        novoManga.setEstoque(Estoque.valueOf(manga.idEstoque()));
        novoManga.setGenero(Genero.valueOf(manga.idGenero()));
        novoManga.setClassificacao(Classificacao.valueOf(manga.idClassificacao()));
        
        Edicao edicao = edicaoRepository.findById(Long.valueOf(manga.idEdicao()));
        if (edicao == null) {
            throw new RuntimeException("Edição não encontrada");
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
    if (edicao == null) {
        throw new RuntimeException("Edição não encontrada");
    }
    manga.setEdicao(List.of(edicao));  

    
    Avaliacao avaliacao = avaliacaoRepository.findById(Long.valueOf(mangaDTO.idAvaliacao()));
    if (avaliacao == null) {
        throw new RuntimeException("Avaliação não encontrada");
    }
    manga.setAvaliacao(List.of(avaliacao));  

  
    Editora editora = editoraRepository.findById(Long.valueOf(mangaDTO.idEditora()));
    if (editora == null) {
        throw new RuntimeException("Editora não encontrada");
    }
    manga.setEditora(editora);

    
    Autor autor = autorRepository.findById(Long.valueOf(mangaDTO.idAutor()));
    if (autor == null) {
        throw new RuntimeException("Autor não encontrado");
    }
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
    return MangaResponseDTO.valueOf(mangaRepository.findById(id));
}


@Override
public MangaResponseDTO findByTitulo(String titulo) {
    return mangaRepository.findByTitulo(titulo).stream()
        .map(manga -> MangaResponseDTO.valueOf(manga)).toList();
}


public List<MangaResponseDTO> findByAutor(Long idAutor) {
    return mangaRepository.findMangasByAutor(idAutor).stream()
        .map(manga -> MangaResponseDTO.valueOf(manga)).toList();
}


public MangaResponseDTO findByEditora(Long idEditora) {
    return mangaRepository.findMangaByEditora(idEditora).stream()
        .map(manga -> MangaResponseDTO.valueOf(manga)).toList();
}

@Override
public List<MangaResponseDTO> findByGenero(String genero) {
    return mangaRepository.findByGenero(genero).stream()
        .map(manga -> MangaResponseDTO.valueOf(manga)).toList();
}

    /*@Override
    public List<MangaResponseDTO> findByGenero(String genero) {
        return mangaRepository.findMangasByGenero(Long.valueOf(genero)).stream()
            .map(MangaResponseDTO::valueOf)
            .toList(); 
    }*/

@Override
public List<MangaResponseDTO> findAll() {
    return mangaRepository.findAll().stream().map(MangaResponseDTO::valueOf).collect(Collectors.toList());
}



}
