package br.manga.resource;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.manga.dto.AutorDTO;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@QuarkusTest
public class AutorResourceTest {

    @Test
    void testFindByNome() {
        given()
            .when().get("/autores/nome/Osamu")
            .then()
            .statusCode(200);
    }

    @Test
    void testFindByNacionalidade() {
        given()
            .when().get("/autores/nacionalidade/Japonesa")
            .then()
            .statusCode(200);
    }

    @Test
    void testCreate() {
        var dto = new AutorDTO("Novo Autor", "Brasileira");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/autores")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    @Test
    void testUpdate() {
        var dto = new AutorDTO("Autor Atualizado", "Japonesa");
        Long id = 1L;

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/autores/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    void testDelete() {
        Long id = 1L;

        given()
            .when().delete("/autores/" + id)
            .then()
            .statusCode(204);
    }
}
