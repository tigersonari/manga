package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    UsuarioService usuarioService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/usuarios")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        UsuarioDTO dto = new UsuarioDTO(
            "Ana Silva",
            "ana.silva@example.com",
            "12345678900",
            "senhaSegura123"
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/usuarios")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Ana Silva"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/usuarios/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testBuscarPorEmail() {
        given()
            .when().get("/usuarios/email?email=ana.silva@example.com")
            .then()
                .statusCode(200);
    }

    @Test
    void testAlterar() {
        UsuarioDTO dto = new UsuarioDTO(
            "Ana Souza",
            "ana.souza@example.com",
            "12345678900",
            "novaSenha123"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/usuarios/" + id)
            .then()
                .statusCode(204);

        UsuarioResponseDTO usuario = usuarioService.findById(id);
        assertThat(usuario.nome(), is("Ana Souza"));
        assertThat(usuario.email(), is("ana.souza@example.com"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/usuarios/" + id)
            .then()
                .statusCode(204);

        UsuarioResponseDTO usuario = usuarioService.findById(id);
        assertNull(usuario);
    }
}
