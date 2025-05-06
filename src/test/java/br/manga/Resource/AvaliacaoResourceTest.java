//OK

package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.service.AvaliacaoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class AvaliacaoResourceTest {

    @Inject
    AvaliacaoService service;

    static Long id;

    @Test
    void testCreate() {
        AvaliacaoDTO dto = new AvaliacaoDTO(8.0, "Avaliação criada via teste", 1L);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/avaliacoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nota", is(8.0f),
                      "comentario", is("Avaliação criada via teste"));
    }


    @Test
    void testBuscarPorIdManga() {
        given()
            .when().get("/avaliacoes/manga/1")
            .then()
                .statusCode(200)
                .body("size()", is(2)); 
    }

    @Test
    void testGetAll() {
        given()
            .when().get("/avaliacoes")
            .then()
                .statusCode(200); 
    }

    @Test
    void testGetById() {
        given()
            .when().get("/avaliacoes/1")
            .then()
                .statusCode(200)
                .body("nota", is(9.5f));
    }

    @Test
    void testGetByNota() {
        given()
            .when().get("/avaliacoes/nota-minima/8.0")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetMediaNotas() {
        given()
            .when().get("/avaliacoes/media/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testUpdate() {
        AvaliacaoDTO dto = new AvaliacaoDTO(7.5, "Comentário antes de atualizar", 2L);
        id = service.create(dto).id();

        AvaliacaoDTO atualizado = new AvaliacaoDTO(9.0, "Comentário atualizado", 2L);

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/avaliacoes/" + id)
            .then()
                .statusCode(204);

        AvaliacaoResponseDTO response = service.findById(id);
        assertThat(response.nota(), is(9.0));
        assertThat(response.comentario(), is("Comentário atualizado"));
    }

    @Test
    void testDelete() {
        AvaliacaoDTO dto = new AvaliacaoDTO(6.0, "Avaliação para deletar", 3L);
        id = service.create(dto).id();

        given()
            .when().delete("/avaliacoes/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/avaliacoes/" + id)
            .then()
                .statusCode(404);
    }
}
