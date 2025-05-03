package br.manga.mg1;

import br.manga.dto.EdicaoDTO;
import br.manga.service.EdicaoService;
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

@Path("/edicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EdicaoResource {

    @Inject EdicaoService service;

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

    @GET @Path("/formato/{formatoId}")
    public Response findByFormato(@PathParam("formatoId") Integer formatoId) {
        return Response.ok(service.findByFormato(formatoId)).build();
    }

    @GET @Path("/status/{statusId}")
    public Response findByStatus(@PathParam("statusId") Integer statusId) {
        return Response.ok(service.findByStatus(statusId)).build();
    }

    @GET @Path("/volume/{volume}/manga/{mangaId}")
    public Response findByVolumeAndManga(
        @PathParam("volume") Integer volume,
        @PathParam("mangaId") Long mangaId) {
        return Response.ok(service.findByVolumeAndManga(volume, mangaId)).build();
    }

    @POST
    public Response create(EdicaoDTO dto) {
        return Response.status(Response.Status.CREATED)
            .entity(service.create(dto))
            .build();
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, EdicaoDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}