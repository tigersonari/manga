package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    EditoraService editoraService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/editoras")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        EditoraDTO dto = new EditoraDTO("JBC", "Some Description", java.time.LocalDate.now());

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/editoras")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("JBC"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/editoras/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testBuscarPorNome() {
        given()
            .when().get("/editoras/nome/JBC")
            .then()
                .statusCode(200);
    }

    @Test
    void testAlterar() {
        EditoraDTO dto = new EditoraDTO("Panini", "Some Description", java.time.LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/editoras/" + id)
            .then()
                .statusCode(204);

        EditoraResponseDTO editora = editoraService.findById(id);
        assertThat(editora.nome(), is("Panini"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/editoras/" + id)
            .then()
                .statusCode(204);

        EditoraResponseDTO editora = editoraService.findById(id);
        assertNull(editora);
    }
}
