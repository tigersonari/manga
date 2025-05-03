package br.manga.resource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import br.manga.dto.EntregaDTO;
import br.manga.service.EntregaService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EntregaResourceTest {

    @Inject
    EntregaService service;

    @Test
    void testCriarEntrega() {
        EntregaDTO dto = new EntregaDTO("ENT123", "Rua A", "PENDENTE", null);

        Long id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/entregas")
            .then()
                .statusCode(201)
                .body("codigo", is("ENT123"))
                .extract().path("id");

        assertEquals("ENT123", service.findById(id).codigoRastreio());
    }

    @Test
    void testBuscarPorCodigo() {
        EntregaDTO dto = new EntregaDTO("ENT456", "Rua B", "ENTREGUE", null);
        service.create(dto);

        given()
            .when().get("/entregas/codigo/ENT456")
            .then()
                .statusCode(200)
                .body("codigo", equalTo("ENT456"));
    }

    @Test
    void testAtualizarEntrega() {
        EntregaDTO dto = new EntregaDTO("ENT789", "Rua C", "PENDENTE", null);
        Long id = service.create(dto).id();

        EntregaDTO atualizada = new EntregaDTO("ENT789", "Rua C", "ENTREGUE", null);

        given()
            .contentType(ContentType.JSON)
            .body(atualizada)
            .when().put("/entregas/" + id)
            .then()
                .statusCode(204);
    }

    @Test
    void testDeletarEntrega() {
        EntregaDTO dto = new EntregaDTO("ENT999", "Rua D", "PENDENTE", null);
        Long id = service.create(dto).id();

        given()
            .when().delete("/entregas/" + id)
            .then()
                .statusCode(204);
    }
}