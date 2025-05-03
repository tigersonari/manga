package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.service.EdicaoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EdicaoResourceTest {

    @Inject
    EdicaoService service;

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/edicoes")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        EdicaoDTO edicao = new EdicaoDTO(1, "Português", LocalDate.now(), "15x21", "Edição Teste", 1, 1, 1, 1L);
        Long id = service.create(edicao).id();

        given()
            .when().get("/edicoes/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("titulo", is("Edição Teste"));
    }

    @Test
    @Order(3)
    void testFindByManga() {
        EdicaoDTO edicao = new EdicaoDTO(2, "Inglês", LocalDate.now(), "15x21", "Edição Manga", 1, 1, 1, 2L);
        service.create(edicao);

        given()
            .when().get("/edicoes/manga/2")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Edição Manga"));
    }

    @Test
    @Order(4)
    void testFindByFormato() {
        EdicaoDTO edicao = new EdicaoDTO(3, "Português", LocalDate.now(), "15x21", "Edição Formato", 2, 1, 1, 3L);
        service.create(edicao);

        given()
            .when().get("/edicoes/formato/2")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Edição Formato"));
    }

    @Test
    @Order(5)
    void testFindByStatus() {
        EdicaoDTO edicao = new EdicaoDTO(4, "Japonês", LocalDate.now(), "15x21", "Edição Status", 1, 1, 2, 4L);
        service.create(edicao);

        given()
            .when().get("/edicoes/status/2")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Edição Status"));
    }

    @Test
    @Order(6)
    void testFindByVolumeAndManga() {
        EdicaoDTO edicao = new EdicaoDTO(5, "Português", LocalDate.now(), "15x21", "Edição Volume", 1, 1, 1, 5L);
        service.create(edicao);

        given()
            .when().get("/edicoes/volume/5/manga/5")
            .then()
                .statusCode(200)
                .body("titulo", is("Edição Volume"));
    }

    @Test
    @Order(7)
    void testCreate() {
        EdicaoDTO edicao = new EdicaoDTO(6, "Inglês", LocalDate.now(), "15x21", "Edição Nova", 1, 1, 1, 6L);

        given()
            .contentType(ContentType.JSON)
            .body(edicao)
            .when().post("/edicoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("titulo", is("Edição Nova"))
                .body("volume", is(6));
    }

    static Long id = null;

    @Test
    @Order(8)
    void testUpdate() {
        EdicaoDTO edicao = new EdicaoDTO(7, "Português", LocalDate.now(), "15x21", "Edição Original", 1, 1, 1, 7L);
        id = service.create(edicao).id();

        EdicaoDTO updated = new EdicaoDTO(7, "Inglês", LocalDate.now(), "15x21", "Edição Atualizada", 1, 1, 1, 7L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/edicoes/" + id)
            .then()
                .statusCode(204);

        EdicaoResponseDTO response = service.findById(id);
        assertThat(response.titulo(), is("Edição Atualizada"));
        assertThat(response.idioma(), is("Inglês"));
    }

    @Test
    @Order(9)
    void testDelete() {
        EdicaoDTO edicao = new EdicaoDTO(8, "Japonês", LocalDate.now(), "15x21", "Edição Deletar", 1, 1, 1, 8L);
        Long idDeletar = service.create(edicao).id();

        given()
            .when().delete("/edicoes/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            EdicaoResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Edição não encontrada"));
        }
    }
}