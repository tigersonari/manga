package br.manga.mg1;

import br.manga.model.Manga;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/mangas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/*@RegisterRestClient(configKey = "manga-api")*/
public class MangaResource {
    
    @GET
    @Path("/{id}")
    public Manga buscarPorId(Long id) {
        return null; 
    }

}
