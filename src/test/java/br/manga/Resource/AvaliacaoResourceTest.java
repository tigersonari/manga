package br.manga.resource;

import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.manga.dto.AvaliacaoDTO;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@QuarkusTest
public class AvaliacaoResourceTest {

    @Test
    void testFindByManga() {
        given()
            .when().get("/avaliacoes/manga/1")
            .then()
            .statusCode(200);
    }

    @Test
    void testFindByNotaGreaterThanEqual() {
        given()
            .when().get("/avaliacoes/nota-minima/3.5")
            .then()
            .statusCode(200);
    }

    @Test
    void testCalcularMediaNotas() {
        given()
            .when().get("/avaliacoes/media/1")
            .then()
            .statusCode(200);
    }

    @Test
    void testCreate() {
        var dto = new AvaliacaoDTO(4.5, "Boa leitura!", 1L);
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/avaliacoes")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    @Test
    void testUpdate() {
        var dto = new AvaliacaoDTO(4.0, "Atualizado", 1L);
        Long id = 1L; 

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/avaliacoes/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    void testDelete() {
        Long id = 1L; 

        given()
            .when().delete("/avaliacoes/" + id)
            .then()
            .statusCode(204);
    }
}
