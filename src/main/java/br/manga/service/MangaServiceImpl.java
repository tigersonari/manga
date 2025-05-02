package br.manga.service;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.model.Classificacao;
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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

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
        // Validação do campo idEstoque
        Estoque estoque = Estoque.valueOf(dto.idEstoque());
        if (estoque == null) {
            throw new WebApplicationException(
                "Valor inválido para idEstoque: " + dto.idEstoque(),
                Response.Status.BAD_REQUEST
            );
        }

        // Validação do campo idGenero
        Genero genero = Genero.valueOf(dto.idGenero());
        if (genero == null) {
            throw new WebApplicationException(
                "Valor inválido para idGenero: " + dto.idGenero() ,
                Response.Status.BAD_REQUEST
            );
        }

        // Validação do campo idClassificacao
        Classificacao classificacao = Classificacao.valueOf(dto.idClassificacao().toString());
        if (classificacao == null) {
            throw new WebApplicationException(
                "Valor inválido para idClassificacao: " + dto.idClassificacao() ,
                Response.Status.BAD_REQUEST
            );
        }

        Manga novoManga = new Manga();
        novoManga.setTitulo(dto.titulo());
        novoManga.setIsbn(dto.isbn());
        novoManga.setLancamento(dto.lancamento());
        novoManga.setPreco(dto.preco());
        novoManga.setSinopse(dto.sinopse().toString());
        novoManga.setEstoque(estoque);
        novoManga.setGenero(genero);
        novoManga.setClassificacao(classificacao);

        if (dto.idEditora() != null) {
            novoManga.setEditora(editoraRepository.findById(dto.idEditora()));
            if (novoManga.getEditora() == null) {
                throw new WebApplicationException(
                    "Editora não encontrada: " + dto.idEditora(),
                    Response.Status.BAD_REQUEST
                );
            }
        }

        if (dto.idAutor() != null) {
            novoManga.setAutor(autorRepository.findById(dto.idAutor()));
            if (novoManga.getAutor() == null) {
                throw new WebApplicationException(
                    "Autor não encontrado: " + dto.idAutor(),
                    Response.Status.BAD_REQUEST
                );
            }
        }

        mangaRepository.persist(novoManga);
        return MangaResponseDTO.valueOf(novoManga);
    }

    @Override
    @Transactional
    public void update(Long id, MangaDTO dto) {
        Manga manga = mangaRepository.findById(id);
        if (manga == null) {
            throw new WebApplicationException("Mangá não encontrado: " + id, Response.Status.NOT_FOUND);
        }

        // Validação do campo idEstoque
        Estoque estoque = Estoque.valueOf(dto.idEstoque());
        if (estoque == null) {
            throw new WebApplicationException(
                "Valor inválido para idEstoque: " + dto.idEstoque() ,
                Response.Status.BAD_REQUEST
            );
        }

        // Validação do campo idGenero
        Genero genero = Genero.valueOf(dto.idGenero());
        if (genero == null) {
            throw new WebApplicationException(
                "Valor inválido para idGenero: " + dto.idGenero() ,
                Response.Status.BAD_REQUEST
            );
        }

        // Validação do campo idClassificacao
        Classificacao classificacao = Classificacao.valueOf(dto.idClassificacao().toString());
        if (classificacao == null) {
            throw new WebApplicationException(
                "Valor inválido para idClassificacao: " + dto.idClassificacao() ,
                Response.Status.BAD_REQUEST
            );
        }

        manga.setTitulo(dto.titulo());
        manga.setIsbn(dto.isbn());
        manga.setLancamento(dto.lancamento());
        manga.setPreco(dto.preco());
        manga.setSinopse(dto.sinopse());
        manga.setEstoque(estoque);
        manga.setGenero(genero);
        manga.setClassificacao(classificacao);

        if (dto.idEditora() != null) {
            manga.setEditora(editoraRepository.findById(dto.idEditora()));
            if (manga.getEditora() == null) {
                throw new WebApplicationException(
                    "Editora não encontrada: " + dto.idEditora(),
                    Response.Status.BAD_REQUEST
                );
            }
        } else {
            manga.setEditora(null);
        }

        if (dto.idAutor() != null) {
            manga.setAutor(autorRepository.findById(dto.idAutor()));
            if (manga.getAutor() == null) {
                throw new WebApplicationException(
                    "Autor não encontrado: " + dto.idAutor(),
                    Response.Status.BAD_REQUEST
                );
            }
        } else {
            manga.setAutor(null);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!mangaRepository.deleteById(id)) {
            throw new WebApplicationException("Mangá não encontrado: " + id, Response.Status.NOT_FOUND);
        }
    }

    @Override
    public MangaResponseDTO findById(Long id) {
        Manga manga = mangaRepository.findById(id);
        if (manga == null) {
            throw new WebApplicationException("Mangá não encontrado: " + id, Response.Status.NOT_FOUND);
        }
        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    public List<MangaResponseDTO> findByTitulo(String titulo) {
        Manga manga = mangaRepository.findByTitulo(titulo);
        if (manga == null) {
            return List.of();
        }
        return List.of(MangaResponseDTO.valueOf(manga));
    }

    @Override
    public List<MangaResponseDTO> findByClassificacao(Integer idClassificacao) {
        return mangaRepository.findByClassificacao(idClassificacao.longValue()).stream()
            .map(MangaResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<MangaResponseDTO> findByEditora(Long idEditora) {
        return mangaRepository.findByEditora(idEditora).stream()
            .map(MangaResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<MangaResponseDTO> findByGenero(Integer idGenero) {
        Genero genero = Genero.valueOf(idGenero);
        if (genero == null) {
            throw new WebApplicationException(
                "Valor inválido para idGenero: " + idGenero + ". Valores válidos: 1 a 2.",
                Response.Status.BAD_REQUEST
            );
        }
        return mangaRepository.findByGenero(genero.toString()).stream()
            .map(MangaResponseDTO::valueOf)
            .toList();
    }

    @Override
    public List<MangaResponseDTO> findAll() {
        return mangaRepository.listAll().stream()
            .map(MangaResponseDTO::valueOf)
            .toList();
    }
}