package br.manga.service;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.model.Autor;
import br.manga.model.Classificacao;
import br.manga.model.Editora;
import br.manga.model.Estoque;
import br.manga.model.Genero;
import br.manga.model.Manga;
import br.manga.repository.AutorRepository;
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

    @Override
    @Transactional
    public MangaResponseDTO create(MangaDTO dto) {
       
        if (mangaRepository.findByIsbn(dto.isbn()) != null) {
            throw new WebApplicationException("ISBN já cadastrado", Response.Status.BAD_REQUEST);
        }

        Manga manga = new Manga();
        setMangaFromDTO(manga, dto);
        mangaRepository.persist(manga);
        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    @Transactional
    public void update(Long id, MangaDTO dto) {
        Manga manga = mangaRepository.findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Manga não encontrado", Response.Status.NOT_FOUND));
        
        Manga existingManga = mangaRepository.findByIsbn(dto.isbn());
        if (existingManga != null && !existingManga.getId().equals(id)) {
            throw new WebApplicationException("ISBN já cadastrado", Response.Status.BAD_REQUEST);
        }
        setMangaFromDTO(manga, dto);
    }

    private void setMangaFromDTO(Manga manga, MangaDTO dto) {
        manga.setTitulo(dto.titulo());
        manga.setIsbn(dto.isbn());
        manga.setLancamento(dto.lancamento());
        manga.setPreco(dto.preco());
        manga.setSinopse(dto.sinopse());

        
        Estoque estoque = Estoque.valueOf(dto.estoqueId());
        if (estoque == null) {
            throw new WebApplicationException("estoqueId inválido: " + dto.estoqueId(), Response.Status.BAD_REQUEST);
        }
        manga.setEstoque(estoque);

        Genero genero = Genero.valueOf(dto.generoId());
        if (genero == null) {
            throw new WebApplicationException("generoId inválido: " + dto.generoId(), Response.Status.BAD_REQUEST);
        }
        manga.setGenero(genero);

        Classificacao classificacao = Classificacao.valueOf(dto.classificacaoId());
        if (classificacao == null) {
            throw new WebApplicationException("classificacaoId inválido: " + dto.classificacaoId(), Response.Status.BAD_REQUEST);
        }
        manga.setClassificacao(classificacao);

        
        if (dto.editoraId() == null) {
            throw new WebApplicationException("editoraId é obrigatório", Response.Status.BAD_REQUEST);
        }
        Editora editora = editoraRepository.findById(dto.editoraId());
        if (editora == null) {
            throw new WebApplicationException("Editora com id " + dto.editoraId() + " não encontrada", Response.Status.NOT_FOUND);
        }
        manga.setEditora(editora);

        
        if (dto.autorId() == null) {
            throw new WebApplicationException("autorId é obrigatório", Response.Status.BAD_REQUEST);
        }
        Autor autor = autorRepository.findById(dto.autorId());
        if (autor == null) {
            throw new WebApplicationException("Autor com id " + dto.autorId() + " não encontrado", Response.Status.NOT_FOUND);
        }
        manga.setAutor(autor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!mangaRepository.deleteById(id)) {
            throw new WebApplicationException("Manga não encontrado", Response.Status.NOT_FOUND);
        }
    }

    @Override
    public MangaResponseDTO findById(Long id) {
        Manga manga = mangaRepository.findById(id);
        if (manga == null) {
            throw new WebApplicationException("Manga não encontrado", Response.Status.NOT_FOUND);
        }
        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    public List<MangaResponseDTO> findByTitulo(String titulo) {
        return mangaRepository.findByTitulo(titulo).stream()
                .map(MangaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<MangaResponseDTO> findByGenero(Integer generoId) {
        return mangaRepository.findByGenero(generoId).stream()
                .map(MangaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<MangaResponseDTO> findByEditora(Long editoraId) {
        return mangaRepository.findByEditora(editoraId).stream()
                .map(MangaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public MangaResponseDTO findByIsbn(String isbn) {
        Manga manga = mangaRepository.findByIsbn(isbn);
        if (manga == null) {
            throw new WebApplicationException("Manga não encontrado", Response.Status.NOT_FOUND);
        }
        return MangaResponseDTO.valueOf(manga);
    }

    @Override
    public List<MangaResponseDTO> findAll() {
        return mangaRepository.listAll().stream()
                .map(MangaResponseDTO::valueOf)
                .toList();
    }
}