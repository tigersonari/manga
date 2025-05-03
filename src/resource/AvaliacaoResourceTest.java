package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    AvaliacaoService avaliacaoService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/avaliacoes")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        AvaliacaoDTO dto = new AvaliacaoDTO(
            5.0,
            "Excelente leitura!",
            1L
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/avaliacoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("comentario", is("Excelente leitura!"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/avaliacoes/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testBuscarPorUsuario() {
        given()
            .when().get("/avaliacoes/usuario/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testBuscarPorManga() {
        given()
            .when().get("/avaliacoes/manga/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testAlterar() {
        AvaliacaoDTO dto = new AvaliacaoDTO(
            4.0,
            "Muito bom, mas poderia ser melhor",
            1L
        );
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/avaliacoes/" + id)
            .then()
                .statusCode(204);

        AvaliacaoResponseDTO avaliacao = avaliacaoService.findById(id);
        assertThat(avaliacao.nota(), is(4));
        assertThat(avaliacao.comentario(), is("Muito bom, mas poderia ser melhor"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/avaliacoes/" + id)
            .then()
                .statusCode(204);

        AvaliacaoResponseDTO avaliacao = avaliacaoService.findById(id);
        assertNull(avaliacao);
    }
}
