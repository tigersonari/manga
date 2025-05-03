package br.manga.mg1;

import br.manga.dto.MangaDTO;
import br.manga.service.MangaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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

@Path("/mangas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MangaResource {

    @Inject
    MangaService service;

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/titulo/{titulo}")
    public Response findByTitulo(@PathParam("titulo") String titulo) {
        return Response.ok(service.findByTitulo(titulo)).build();
    }

    @GET
    @Path("/genero/{generoId}")
    public Response findByGenero(@PathParam("generoId") Integer generoId) {
        return Response.ok(service.findByGenero(generoId)).build();
    }

    @GET
    @Path("/editora/{editoraId}")
    public Response findByEditora(@PathParam("editoraId") Long editoraId) {
        return Response.ok(service.findByEditora(editoraId)).build();
    }

    @GET
    @Path("/isbn/{isbn}")
    public Response findByIsbn(@PathParam("isbn") String isbn) {
        return Response.ok(service.findByIsbn(isbn)).build();
    }

    @POST
    public Response create(@Valid MangaDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid MangaDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}