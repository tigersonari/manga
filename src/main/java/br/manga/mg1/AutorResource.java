package br.manga.mg1;


import java.util.List;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;
import br.manga.service.AutorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorResource {

    @Inject
    AutorService service;

    @GET
    public List<AutorResponseDTO> buscarTodos() { 
        return service.findAll();
    }

    @GET
    @Path("/nome/{nome}")
    public AutorResponseDTO buscarPorNome(String nome) { 
        return service.findByNome(nome);
    }

    @GET
    @Path("/nacionalidade/{nacionalidade}")
    public List<AutorResponseDTO> buscarPorNacionalidade(String nacionalidade) { 
        return service.findByNacionalidade(nacionalidade);
    }

    @POST
    public AutorResponseDTO incluir(AutorDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, AutorDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }
}
