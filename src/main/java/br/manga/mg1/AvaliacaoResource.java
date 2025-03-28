package br.manga.mg1;

import java.util.List;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.service.AvaliacaoService;
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

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    @Inject
    AvaliacaoService service;

    @GET
    public List<AvaliacaoResponseDTO> buscarTodos() { 
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public AvaliacaoResponseDTO buscarPorId(Long id) { 
        return service.findById(id);
    }

   /*  @GET
    @Path("/avaliacao/{idManga}")
    public List<AvaliacaoResponseDTO> buscarPorMangaId(Long idManga) { 
        return service.findByMangaId(idManga);
    }*/

    @POST
    public AvaliacaoResponseDTO incluir(AvaliacaoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar( Long id, AvaliacaoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar( Long id) {
        service.delete(id);
    }
}
