package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.manga.dto.EditoraDTO;
import br.manga.service.EditoraService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EditoraResourceTest {

    @Inject
    EditoraService editoraService;

    @Test
    void testCreate() {
        EditoraDTO dto = new EditoraDTO(null, "Panini", LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/editoras")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", equalTo("Panini"));
    }

    @Test
    void testUpdate() {
        EditoraDTO dto = new EditoraDTO(null, "JBC", LocalDate.now());
        Long id = editoraService.create(dto).id();

        EditoraDTO atualizado = new EditoraDTO(null, "JBC Atualizada", LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/editoras/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    void testBuscarPorSede() {
        EditoraDTO dto = new EditoraDTO("JBC", "São Paulo", LocalDate.now());
        editoraService.create(dto);

        given()
            .when().get("/editoras/sede/São Paulo")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1))
                .body("[0].sede", equalTo("São Paulo"));
    }

    @Test
    void testDelete() {
        EditoraDTO dto = new EditoraDTO(null, "NewPop", LocalDate.now());
        Long id = editoraService.create(dto).id();

        given()
            .when().delete("/editoras/" + id)
            .then()
            .statusCode(204);
    }
}