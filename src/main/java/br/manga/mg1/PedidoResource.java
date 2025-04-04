package br.manga.mg1;

import java.util.List;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
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

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @POST
    public PedidoResponseDTO create(PedidoDTO pedido) {
        return pedidoService.create(pedido);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, PedidoDTO pedido) {
        pedidoService.update(id, pedido);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        pedidoService.delete(id);
    }

    @GET
    @Path("/{id}")
    public PedidoResponseDTO findById(@PathParam("id") Long id) {
        return pedidoService.findById(id);
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public List<PedidoResponseDTO> findByUsuario(@PathParam("idUsuario") Long idUsuario) {
        return pedidoService.findByUsuario(idUsuario);
    }

    @GET
    @Path("/status/{status}")
    public List<PedidoResponseDTO> findByStatus(@PathParam("status") String status) {
        return pedidoService.findByStatus(status);
    }

    @GET
    public List<PedidoResponseDTO> findAll() {
        return pedidoService.findAll();
    }
}
