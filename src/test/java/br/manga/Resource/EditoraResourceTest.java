package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;
import br.manga.service.EditoraService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EditoraResourceTest {

    @Inject
    EditoraService service;

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/editoras")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        EditoraDTO editora = new EditoraDTO("Editora Teste", "São Paulo", LocalDate.of(2000, 1, 1));
        Long id = service.create(editora).id();

        given()
            .when().get("/editoras/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Editora Teste"));
    }

    @Test
    @Order(3)
    void testFindByNome() {
        EditoraDTO editora = new EditoraDTO("Editora Nome", "Rio de Janeiro", LocalDate.of(2010, 1, 1));
        service.create(editora);

        given()
            .when().get("/editoras/nome/Editora Nome")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Editora Nome"));
    }

    @Test
    @Order(4)
    void testFindBySede() {
        EditoraDTO editora = new EditoraDTO("Editora Sede", "Belo Horizonte", LocalDate.of(2015, 1, 1));
        service.create(editora);

        given()
            .when().get("/editoras/sede/Belo Horizonte")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Editora Sede"));
    }

    @Test
    @Order(5)
    void testFindByAnoFundacao() {
        EditoraDTO editora = new EditoraDTO("Editora Ano", "Curitiba", LocalDate.of(2020, 1, 1));
        service.create(editora);

        given()
            .when().get("/editoras/ano-fundacao/2020")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Editora Ano"));
    }

    @Test
    @Order(6)
    void testCreate() {
        EditoraDTO editora = new EditoraDTO("Editora Nova", "Porto Alegre", LocalDate.of(2021, 1, 1));

        given()
            .contentType(ContentType.JSON)
            .body(editora)
            .when().post("/editoras")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Editora Nova"))
                .body("sede", is("Porto Alegre"));
    }

    static Long id = null;

    @Test
    @Order(7)
    void testUpdate() {
        EditoraDTO editora = new EditoraDTO("Editora Original", "Original", LocalDate.of(2019, 1, 1));
        id = service.create(editora).id();

        EditoraDTO updated = new EditoraDTO("Editora Atualizada", "Atualizada", LocalDate.of(2019, 1, 1));

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/editoras/" + id)
            .then()
                .statusCode(204);

        EditoraResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Editora Atualizada"));
        assertThat(response.sede(), is("Atualizada"));
    }

    @Test
    @Order(8)
    void testDelete() {
        EditoraDTO editora = new EditoraDTO("Editora Deletar", "Deletar", LocalDate.of(2018, 1, 1));
        Long idDeletar = service.create(editora).id();

        given()
            .when().delete("/editoras/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            EditoraResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Editora não encontrada"));
        }
    }
}