package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.manga.dto.EdicaoDTO;
import br.manga.service.EdicaoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EdicaoResourceTest {

    @Inject
    EdicaoService edicaoService;

    @Test
    void testCreate() {
        EdicaoDTO dto = new EdicaoDTO(null, "Manga Title", LocalDate.of(2023, 1, 1), "Author Name", "Publisher Name", 2023, 1, 1, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/edicoes")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("ano", equalTo(2023))
            .body("preco", equalTo(12.5F));
    }

    @Test
    void testUpdate() {
        EdicaoDTO dto = new EdicaoDTO(null, "Manga Title", LocalDate.of(2023, 1, 1), "Author Name", "Publisher Name", 2023, 1, 1, 1L);
        Long id = edicaoService.create(dto).id();

        EdicaoDTO atualizado = new EdicaoDTO(null, "Updated Manga Title", LocalDate.of(2024, 1, 1), "Updated Author", "Updated Publisher", 2024, 1, 1, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/edicoes/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    void testGetAllEdicoes() {
        given()
        .when().get("/edicoes")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void testDelete() {
        EdicaoDTO dto = new EdicaoDTO(null, "Manga Title", LocalDate.of(2023, 1, 1), "Author Name", "Publisher Name", 2023, 1, 1, 1L);
        Long id = edicaoService.create(dto).id();

        given()
            .when().delete("/edicoes/" + id)
            .then()
            .statusCode(204);
    }
}