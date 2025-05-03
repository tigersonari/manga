package br.manga.mg1;

import br.manga.dto.PedidoDTO;
import br.manga.service.PedidoService;
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

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

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
    @Path("/usuario/{usuarioId}")
    public Response findByUsuario(@PathParam("usuarioId") Long usuarioId) {
        return Response.ok(service.findByUsuario(usuarioId)).build();
    }

    @GET
    @Path("/status/{status}")
    public Response findByStatus(@PathParam("status") String status) {
        return Response.ok(service.findByStatus(status)).build();
    }

    @GET
    @Path("/numero/{numeroPedido}")
    public Response findByNumeroPedido(@PathParam("numeroPedido") Long numeroPedido) {
        return Response.ok(service.findByNumeroPedido(numeroPedido)).build();
    }

    @POST
    public Response create(PedidoDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PedidoDTO dto) {
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