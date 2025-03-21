package br.manga.mg1;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.service.MangaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/mangashounen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MangaResource {

    @Inject
    MangaService service;


    @GET
    public List<MangaResponseDTO> buscarTodos() {

            return service.findAll();

    }

    @GET
    @Path("/sigla/{sigla}")
    public MangaResponseDTO buscarPorSigla(String sigla) {
        return service.findBySigla(sigla);
    }

    @GET
    @Path("/id/{id}")
    public MangaResponseDTO buscarPorId(Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    public MangaResponseDTO incluir(MangaDTO dto) {

        return service.create(dto);

    }

    @PUT
    @Path("/{id}") 
    public void alterar(@PathParam("id") long id, MangaDTO dto) {
        service.update(dto, id);
    }
   

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(@PathParam("id") Long id) {
       service.delete(id);
    }
}