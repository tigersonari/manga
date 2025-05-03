package br.manga.mg1;

import br.manga.dto.EditoraDTO;
import br.manga.service.EditoraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/editoras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EditoraResource {

    @Inject EditoraService service;

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET @Path("/sede/{sede}")
    public Response findBySede(@PathParam("sede") String sede) {
        return Response.ok(service.findBySede(sede)).build();
    }

    @GET @Path("/ano-fundacao/{ano}")
    public Response findByAnoFundacao(@PathParam("ano") int ano) {
        return Response.ok(service.findByAnoFundacao(ano)).build();
    }

    @POST
    public Response create(EditoraDTO dto) {
        return Response.status(Response.Status.CREATED)
            .entity(service.create(dto))
            .build();
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, EditoraDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}