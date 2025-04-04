package br.manga.mg1;

import java.util.List;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;
import br.manga.service.UsuarioService;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @POST
    public UsuarioResponseDTO create(UsuarioDTO usuario) {
        return usuarioService.create(usuario);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, UsuarioDTO usuario) {
        usuarioService.update(id, usuario);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return usuarioService.findById(id);
    }

    @GET
    @Path("/email/{email}")
    public UsuarioResponseDTO findByEmail(@PathParam("email") String email) {
        return usuarioService.findByEmail(email);
    }

    @GET
    @Path("/usuario/{nome}")
    public UsuarioResponseDTO findByNome(@PathParam("nome") String nome) {
        return usuarioService.findByNome(nome);
    }

    @GET
    public List<UsuarioResponseDTO> findAll() {
        return usuarioService.findAll();
    }
}
