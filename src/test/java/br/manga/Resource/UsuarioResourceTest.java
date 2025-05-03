package br.manga.resource;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.manga.dto.UsuarioDTO;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@QuarkusTest
public class UsuarioResourceTest {

    @Test
    void testFindByEmail() {
        given()
            .when().get("/usuarios/email/usuario@email.com")
            .then()
            .statusCode(200);
    }

    @Test
    void testFindByNome() {
        given()
            .when().get("/usuarios/nome/Carlos")
            .then()
            .statusCode(200);
    }

    @Test
    void testCreate() {
        var dto = new UsuarioDTO(null, "Carlos", "usuario@email.com", "senha123");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/usuarios")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    @Test
    void testUpdate() {
        var dto = new UsuarioDTO(null, "Carlos Atualizado", "email@novo.com", "novaSenha");
        Long id = 1L;

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/usuarios/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    void testDelete() {
        Long id = 1L;

        given()
            .when().delete("/usuarios/" + id)
            .then()
            .statusCode(204);
    }
}
