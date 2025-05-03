package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    AutorService autorService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/autores")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        AutorDTO dto = new AutorDTO(
            "Tite Kubo",
            "Japonês"
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/autores")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Tite Kubo"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/autores/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testBuscarPorNome() {
        given()
            .when().get("/autores/nome/Tite Kubo")
            .then()
                .statusCode(200);
    }

    @Test
    void testBuscarPorNacionalidade() {
        given()
            .when().get("/autores/nome/nacionalidade")
            .then()
                .statusCode(200);
    }

    @Test
    void testAlterar() {
        AutorDTO dto = new AutorDTO(
            "Tite Kubo (Alterado)",
            "Japonês"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/autores/" + id)
            .then()
                .statusCode(204);

        AutorResponseDTO autor = autorService.findById(id);
        assertThat(autor.nome(), is("Tite Kubo (Alterado)"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/autores/" + id)
            .then()
                .statusCode(204);

        AutorResponseDTO autor = autorService.findById(id);
        assertNull(autor);
    }
}
