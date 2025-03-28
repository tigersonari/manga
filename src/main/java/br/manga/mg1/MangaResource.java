package br.manga.mg1;

import java.util.List;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.service.MangaService;
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

@Path("/mangas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/*@RegisterRestClient(configKey = "manga-api")*/
public class MangaResource {

    @Inject
    MangaService service;

    
     @GET
    public List<MangaResponseDTO> buscarTodos() { 
        return service.findAll();
    }

    @GET
    @Path("/titulo/{titulo}")
    public MangaResponseDTO buscarPorTitulo(String titulo) { 
        return service.findByTitulo(titulo);
    }

    @GET
    @Path("/autor/{autor}")
    public List<MangaResponseDTO> buscarPorAutor(String autor) { 
        return service.findByAutor(autor);
    }

    @GET
    @Path("/editora/{editora}")
    public List<MangaResponseDTO> buscarPorEditora(String editora) { 
        return service.findByEditora(editora);
    }

    @GET
    @Path("/genero/{genero}")
    public List<MangaResponseDTO> buscarPorGenero( String genero) { 
        return service.findByGenero(genero);
    }


    @POST
    public MangaResponseDTO incluir(MangaDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, MangaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }
}


