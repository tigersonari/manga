package br.manga.mg1;

import java.util.List;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;
import br.manga.service.PagamentoService;
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

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
    PagamentoService pagamentoService;

    @POST
    public PagamentoResponseDTO create(PagamentoDTO pagamento) {
        return pagamentoService.create(pagamento);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, PagamentoDTO pagamento) {
        pagamentoService.update(id, pagamento);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        pagamentoService.delete(id);
    }

    @GET
    @Path("/{id}")
    public PagamentoResponseDTO findById(@PathParam("id") Long id) {
        return pagamentoService.findById(id);
    }

    @GET
    @Path("/pedido/{idPedido}")
    public List<PagamentoResponseDTO> findByPedido(@PathParam("idPedido") Long idPedido) {
        return pagamentoService.findByPedido(idPedido);
    }

    @GET
    @Path("/status/{status}")
    public List<PagamentoResponseDTO> findByStatus(@PathParam("status") String status) {
        return pagamentoService.findByStatus(status);
    }

    @GET
    public List<PagamentoResponseDTO> findAll() {
        return pagamentoService.findAll();
    }
}
