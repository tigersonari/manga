package br.manga.service;

import java.util.List;
import java.util.stream.Collectors;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.model.Classificacao;
import br.manga.model.Edicao;
import br.manga.model.Estoque;
import br.manga.model.Genero;
import br.manga.model.Manga;
import br.manga.repository.AutorRepository;
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
    EditoraRepository editoraRepository;

    @Inject
    AutorRepository autorRepository;

    @Inject
    EdicaoRepository edicaoRepository;

    @Override
    @Transactional
    public MangaResponseDTO create(MangaDTO dto) {
        Manga manga = new Manga();
        manga.setTitulo(dto.titulo());
        manga.setIsbn(dto.isbn());
        manga.setLancamento(dto.lancamento());
        manga.setPreco(dto.preco());
        manga.setSinopse(dto.sinopse());
        manga.setEstoque(Estoque.valueOf(dto.idEstoque().toString()));
        manga.setGenero(Genero.valueOf(dto.idGenero().intValue()));
        manga.setClassificacao(Classificacao.valueOf(dto.idClassificacao().toString()));
        manga.setEditora(editoraRepository.findById(dto.idEditora()));
        manga.setAutor(autorRepository.findById(dto.idAutor()));

        if (dto.idsEdicoes() != null) {
            List<Edicao> edicoes = dto.idsEdicoes().stream()
                .map(id -> edicaoRepository.findById(id))
                .collect(Collectors.toList());
            manga.setEdicoes(edicoes);
        }

        mangaRepository.persist(manga);
        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    @Transactional
    public void update(Long id, MangaDTO dto) {
        Manga manga = mangaRepository.findById(id);
        manga.setTitulo(dto.titulo());
        manga.setIsbn(dto.isbn());
        manga.setLancamento(dto.lancamento());
        manga.setPreco(dto.preco());
        manga.setSinopse(dto.sinopse());
        manga.setEstoque(Estoque.valueOf(dto.idEstoque().toString()));
        manga.setGenero(Genero.valueOf(dto.idGenero().intValue()));
        manga.setClassificacao(Classificacao.valueOf(dto.idClassificacao().toString()));
        manga.setEditora(editoraRepository.findById(dto.idEditora()));
        manga.setAutor(autorRepository.findById(dto.idAutor()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        mangaRepository.deleteById(id);
    }

    @Override
    public MangaResponseDTO findById(Long id) {
        return MangaResponseDTO.valueOf(mangaRepository.findById(id));
    }

    @Override
public List<MangaResponseDTO> findByTitulo(String titulo) {
        List<Manga> mangas = List.of(mangaRepository.findByTitulo(titulo));
        return mangas.stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<MangaResponseDTO> findByClassificacao(Long idClassificacao) {
        return mangaRepository.findByClassificacao(idClassificacao).stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<MangaResponseDTO> findByEditora(Long idEditora) {
        return mangaRepository.findByEditora(idEditora).stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
}

    @Override
    public List<MangaResponseDTO> findByGenero(Long idGenero) {
        return mangaRepository.findByGenero(idGenero.toString()).stream()
            .map(MangaResponseDTO::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<MangaResponseDTO> findAll() {
        return mangaRepository.listAll().stream()
            .map(MangaResponseDTO::valueOf)
            .toList();
    }
}