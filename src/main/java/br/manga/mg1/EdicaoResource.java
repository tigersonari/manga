package br.manga.mg1;

import java.time.LocalDate;
import java.util.List;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.service.EdicaoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/edicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EdicaoResource {

    @Inject
    EdicaoService service;

    @GET
    public List<EdicaoResponseDTO> buscarTodos() { 
        return service.findAll();
    }

    @GET
    @Path("/volume/{volume}")
    public List<EdicaoResponseDTO> buscarPorVolume(Integer volume) { 
        return service.findByVolume(volume);
    }

    @GET
    @Path("/manga/{idManga}")
    public List<EdicaoResponseDTO> buscarPorMangaId(Long idManga) { 
        return service.findByMangaId(idManga);
    }

    @GET
    @Path("/lancamento/{lancamento}")
    public List<EdicaoResponseDTO> buscarPorLancamento(String lancamento) { 
        return service.findByLancamento(LocalDate.parse(lancamento));
    }
    /*tirar dúvida sobre lancamento com localdate */

    @GET
    @Path("/idioma/{idioma}")
    public List<EdicaoResponseDTO> buscarPorIdioma(String idioma) { 
        return service.findByIdioma(idioma);
    }

    @POST
    public EdicaoResponseDTO incluir(EdicaoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, EdicaoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }
}
