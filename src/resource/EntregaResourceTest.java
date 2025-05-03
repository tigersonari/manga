package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;
import br.manga.service.EntregaService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EntregaResourceTest {

    @Inject
    EntregaService entregaService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/entregas")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        EntregaDTO dto = new EntregaDTO(
            "Rua das Flores, 123",
            "Centro",
            "São Paulo",
            12345678L
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/entregas")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("cidade", is("São Paulo"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/entregas/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testAlterar() {
        EntregaDTO dto = new EntregaDTO(
            "Rua das Florinda, 163",
            "Centro",
            "São Paulo",
            12345678L
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/entregas/" + id)
            .then()
                .statusCode(204);

        EntregaResponseDTO entrega = entregaService.findById(id);
        assertThat(entrega.endereco(), is("Rio de Janeiro"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/entregas/" + id)
            .then()
                .statusCode(204);

        EntregaResponseDTO entrega = entregaService.findById(id);
        assertNull(entrega);
    }
}
