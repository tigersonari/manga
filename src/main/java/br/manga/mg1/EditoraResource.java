package br.manga.mg1;


import java.time.LocalDate;
import java.util.List;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;
import br.manga.service.EditoraService;
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

@Path("/editoras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EditoraResource {

    @Inject
    EditoraService service;

    @GET
    public List<EditoraResponseDTO> buscarTodos() { 
        return service.findAll();
    }

    @GET
    @Path("/nome/{nome}")
    public List<EditoraResponseDTO> buscarPorNome(String nome) { 
        return service.findByNome(nome);
    }

    @GET
    @Path("/sede/{sede}")
    public List<EditoraResponseDTO> buscarPorSede(String sede) { 
        return service.findBySede(sede);
    }

    @GET
    @Path("/fundacao/{fundacao}")
    public List<EditoraResponseDTO> buscarPorFundacao(String fundacao) { 
        return service.findByFundacao(LocalDate.parse(fundacao));
    }

    @POST
    public EditoraResponseDTO incluir(EditoraDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, EditoraDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }
}
