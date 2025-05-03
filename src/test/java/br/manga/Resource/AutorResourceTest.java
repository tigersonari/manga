package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;
import br.manga.service.AutorService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class AutorResourceTest {

    @Inject
    AutorService service;

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/autores")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        AutorDTO autor = new AutorDTO("Autor Teste", "Brasileiro");
        Long id = service.create(autor).id();

        given()
            .when().get("/autores/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Autor Teste"));
    }

    @Test
    @Order(3)
    void testFindByNome() {
        AutorDTO autor = new AutorDTO("Autor Nome", "Japonês");
        service.create(autor);

        given()
            .when().get("/autores/nome/Autor Nome")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Autor Nome"));
    }

    @Test
    @Order(4)
    void testFindByNacionalidade() {
        AutorDTO autor = new AutorDTO("Autor Nacionalidade", "Americano");
        service.create(autor);

        given()
            .when().get("/autores/nacionalidade/Americano")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Autor Nacionalidade"));
    }

    @Test
    @Order(5)
    void testCreate() {
        AutorDTO autor = new AutorDTO("Autor Novo", "Coreano");

        given()
            .contentType(ContentType.JSON)
            .body(autor)
            .when().post("/autores")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Autor Novo"))
                .body("nacionalidade", is("Coreano"));
    }

    static Long id = null;

    @Test
    @Order(6)
    void testUpdate() {
        AutorDTO autor = new AutorDTO("Autor Original", "Original");
        id = service.create(autor).id();

        AutorDTO updated = new AutorDTO("Autor Atualizado", "Atualizado");

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/autores/" + id)
            .then()
                .statusCode(204);

        AutorResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Autor Atualizado"));
        assertThat(response.nacionalidade(), is("Atualizado"));
    }

    @Test
    @Order(7)
    void testDelete() {
        AutorDTO autor = new AutorDTO("Autor Deletar", "Deletar");
        Long idDeletar = service.create(autor).id();

        given()
            .when().delete("/autores/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            AutorResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Autor não encontrado"));
        }
    }
}