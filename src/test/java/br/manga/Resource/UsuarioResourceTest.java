//ok

package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import br.manga.dto.UsuarioDTO;
import br.manga.dto.UsuarioResponseDTO;
import br.manga.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService service;

    static Long id;

    @Test
    void testCreate() {
        UsuarioDTO dto = new UsuarioDTO("user1", "user1@mail.com", "password123", "rua testew");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/usuarios")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("user1"));
    }

    @Test
    void testGetAll() {
        given()
        .when().get("/usuarios")
        .then()
            .statusCode(200);   
    }

    @Test
    void testGetById() {
        given()
            .when().get("/usuarios/1")
            .then()
                .statusCode(200)
                .body("nome", is("João Silva"));
    }

    @Test
    void testGetByEmail() {
        given()
            .when().get("/usuarios/email/joao@email.com")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByNome() {
        given()
            .when().get("/usuarios/nome/user1")
            .then()
                .statusCode(200);
    }

    @Test
    void testUpdate() {
        UsuarioDTO dto = new UsuarioDTO("user1", "user7@mail.com", "password123", "rua testew");
        id = service.create(dto).id();

        UsuarioDTO updated = new UsuarioDTO("user2", "user9@mail.com", "newPassword123", "updated address");

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/usuarios/" + id)
            .then()
                .statusCode(204);

        UsuarioResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("user2"));
    }

    @Test
    void testDelete() {
        UsuarioDTO dto = new UsuarioDTO("user1", "user6@mail.com", "password123", "default address");
        id = service.create(dto).id();

        given()
            .when().delete("/usuarios/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/usuarios/" + id)
            .then()
                .statusCode(404);
    }
}
