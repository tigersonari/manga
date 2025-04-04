package br.manga.mg1;

import java.util.List;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;
import br.manga.service.EntregaService;
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

@Path("/entregas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntregaResource {

    @Inject
    EntregaService entregaService;

    @POST
    public EntregaResponseDTO create(EntregaDTO entrega) {
        return entregaService.create(entrega);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, EntregaDTO entrega) {
        entregaService.update(id, entrega);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        entregaService.delete(id);
    }

    @GET
    @Path("/{id}")
    public EntregaResponseDTO findById(@PathParam("id") Long id) {
        return entregaService.findById(id);
    }

    @GET
    @Path("/pedido/{idPedido}")
    public EntregaResponseDTO findByPedido(@PathParam("idPedido") Long idPedido) {
        return entregaService.findByPedido(idPedido);
    }

    @GET
    @Path("/codigo/{codigoRastreio}")
    public EntregaResponseDTO findByCodigoRastreio(@PathParam("codigoRastreio") String codigoRastreio) {
        return entregaService.findByCodigoRastreio(codigoRastreio);
    }

    @GET
    @Path("/status/{status}")
    public List<EntregaResponseDTO> findByStatus(@PathParam("status") String status) {
        return entregaService.findByStatus(status);
    }

    @GET
    public List<EntregaResponseDTO> findAll() {
        return entregaService.findAll();
    }
}
