package br.manga.mg1;

import br.manga.dto.AvaliacaoDTO;
import br.manga.service.AvaliacaoService;
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

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    @Inject AvaliacaoService service;

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET @Path("/manga/{mangaId}")
    public Response findByManga(@PathParam("mangaId") Long mangaId) {
        return Response.ok(service.findByManga(mangaId)).build();
    }

    @GET @Path("/nota-minima/{nota}")
    public Response findByNotaGreaterThanEqual(@PathParam("nota") Double nota) {
        return Response.ok(service.findByNotaGreaterThanEqual(nota)).build();
    }

    @GET @Path("/media/{mangaId}")
    public Response calcularMediaNotas(@PathParam("mangaId") Long mangaId) {
        return Response.ok(service.calcularMediaNotas(mangaId)).build();
    }

    @POST
    public Response create(AvaliacaoDTO dto) {
        return Response.status(Response.Status.CREATED)
            .entity(service.create(dto))
            .build();
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, AvaliacaoDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}