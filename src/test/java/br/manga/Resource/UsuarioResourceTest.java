package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
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

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/usuarios")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        UsuarioDTO usuario = new UsuarioDTO("Usuario Teste", "teste@email.com", "12345678901", "Rua Teste 123");
        Long id = service.create(usuario).id();

        given()
            .when().get("/usuarios/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Usuario Teste"));
    }

    @Test
    @Order(3)
    void testFindByEmail() {
        UsuarioDTO usuario = new UsuarioDTO("Usuario Email", "email@teste.com", "12345678901", "Rua Email 124");
        service.create(usuario);

        given()
            .when().get("/usuarios/email/email@teste.com")
            .then()
                .statusCode(200)
                .body("nome", is("Usuario Email"));
    }

    @Test
    @Order(4)
    void testFindByNome() {
        UsuarioDTO usuario = new UsuarioDTO("Usuario Nome", "nome@teste.com", "12345678901", "Rua Nome 125");
        service.create(usuario);

        given()
            .when().get("/usuarios/nome/Usuario Nome")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Usuario Nome"));
    }

    @Test
    @Order(5)
    void testCreate() {
        UsuarioDTO usuario = new UsuarioDTO("Usuario Novo", "novo@teste.com", "12345678901", "Rua Nova 126");

        given()
            .contentType(ContentType.JSON)
            .body(usuario)
            .when().post("/usuarios")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Usuario Novo"))
                .body("email", is("novo@teste.com"));
    }

    static Long id = null;

    @Test
    @Order(6)
    void testUpdate() {
        UsuarioDTO usuario = new UsuarioDTO("Usuario Original", "original@teste.com", "12345678901", "Rua Original 127");
        id = service.create(usuario).id();

        UsuarioDTO updated = new UsuarioDTO("Usuario Atualizado", "atualizado@teste.com", "12345678901", "Rua Atualizada 127");

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/usuarios/" + id)
            .then()
                .statusCode(204);

        UsuarioResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Usuario Atualizado"));
        assertThat(response.email(), is("atualizado@teste.com"));
    }

    @Test
    @Order(7)
    void testDelete() {
        UsuarioDTO usuario = new UsuarioDTO("Usuario Deletar", "deletar@teste.com", "12345678901", "Rua Deletar 128");
        Long idDeletar = service.create(usuario).id();

        given()
            .when().delete("/usuarios/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            UsuarioResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Usuário não encontrado"));
        }
    }
}