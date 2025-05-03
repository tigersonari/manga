package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
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

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/avaliacoes")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(8.5, "Ótimo mangá!", 1L);
        Long id = service.create(avaliacao).id();

        given()
            .when().get("/avaliacoes/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nota", is(8.5f));
    }

    @Test
    @Order(3)
    void testFindByManga() {
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(9.0, "Fantástico!", 2L);
        service.create(avaliacao);

        given()
            .when().get("/avaliacoes/manga/2")
            .then()
                .statusCode(200)
                .body("[0].nota", is(9.0f));
    }

    @Test
    @Order(4)
    void testFindByNotaGreaterThanEqual() {
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(7.5, "Bom!", 3L);
        service.create(avaliacao);

        given()
            .when().get("/avaliacoes/nota-minima/7.0")
            .then()
                .statusCode(200)
                .body("[0].nota", is(7.5f));
    }

    @Test
    @Order(5)
    void testCalcularMediaNotas() {
        AvaliacaoDTO avaliacao1 = new AvaliacaoDTO(8.0, "Muito bom!", 4L);
        service.create(avaliacao1);

        AvaliacaoDTO avaliacao2 = new AvaliacaoDTO(9.0, "Excelente!", 4L);
        service.create(avaliacao2);

        given()
            .when().get("/avaliacoes/media/4")
            .then()
                .statusCode(200)
                .body(is("8.5"));
    }

    @Test
    @Order(6)
    void testCreate() {
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(8.8, "Adorei!", 5L);

        given()
            .contentType(ContentType.JSON)
            .body(avaliacao)
            .when().post("/avaliacoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nota", is(8.8f))
                .body("comentario", is("Adorei!"));
    }

    static Long id = null;

    @Test
    @Order(7)
    void testUpdate() {
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(7.0, "Regular", 6L);
        id = service.create(avaliacao).id();

        AvaliacaoDTO updated = new AvaliacaoDTO(9.5, "Muito melhor!", 6L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/avaliacoes/" + id)
            .then()
                .statusCode(204);

        AvaliacaoResponseDTO response = service.findById(id);
        assertThat(response.nota(), is(9.5));
        assertThat(response.comentario(), is("Muito melhor!"));
    }

    @Test
    @Order(8)
    void testDelete() {
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(6.5, "Para deletar", 7L);
        Long idDeletar = service.create(avaliacao).id();

        given()
            .when().delete("/avaliacoes/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            AvaliacaoResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Avaliação não encontrada"));
        }
    }
}