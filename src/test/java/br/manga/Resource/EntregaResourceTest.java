package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
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
    EntregaService service;

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/entregas")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        EntregaDTO entrega = new EntregaDTO("Rua Teste 123", "ABC123", "PENDENTE", 1L);
        Long id = service.create(entrega).id();

        given()
            .when().get("/entregas/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("codigoRastreio", is("ABC123"));
    }

    @Test
    @Order(3)
    void testFindByStatus() {
        EntregaDTO entrega = new EntregaDTO("Rua Teste 124", "ABC124", "EM_TRANSITO", 2L);
        service.create(entrega);

        given()
            .when().get("/entregas/status/EM_TRANSITO")
            .then()
                .statusCode(200)
                .body("[0].codigoRastreio", is("ABC124"));
    }

    @Test
    @Order(4)
    void testFindByCodigoRastreio() {
        EntregaDTO entrega = new EntregaDTO("Rua Teste 125", "ABC125", "PENDENTE", 3L);
        service.create(entrega);

        given()
            .when().get("/entregas/rastreio/ABC125")
            .then()
                .statusCode(200)
                .body("codigoRastreio", is("ABC125"));
    }

    @Test
    @Order(5)
    void testFindByPedido() {
        EntregaDTO entrega = new EntregaDTO("Rua Teste 126", "ABC126", "ENTREGUE", 4L);
        service.create(entrega);

        given()
            .when().get("/entregas/pedido/4")
            .then()
                .statusCode(200)
                .body("[0].codigoRastreio", is("ABC126"));
    }

    @Test
    @Order(6)
    void testCreate() {
        EntregaDTO entrega = new EntregaDTO("Rua Nova 127", "ABC127", "NOVO", 5L);

        given()
            .contentType(ContentType.JSON)
            .body(entrega)
            .when().post("/entregas")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("codigoRastreio", is("ABC127"))
                .body("status", is("NOVO"));
    }

    static Long id = null;

    @Test
    @Order(7)
    void testUpdate() {
        EntregaDTO entrega = new EntregaDTO("Rua Original 128", "ABC128", "ORIGINAL", 6L);
        id = service.create(entrega).id();

        EntregaDTO updated = new EntregaDTO("Rua Atualizada 128", "ABC128", "ATUALIZADO", 6L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/entregas/" + id)
            .then()
                .statusCode(204);

        EntregaResponseDTO response = service.findById(id);
        assertThat(response.status(), is("ATUALIZADO"));
        assertThat(response.endereco(), is("Rua Atualizada 128"));
    }

    @Test
    @Order(8)
    void testDelete() {
        EntregaDTO entrega = new EntregaDTO("Rua Deletar 129", "ABC129", "DELETAR", 7L);
        Long idDeletar = service.create(entrega).id();

        given()
            .when().delete("/entregas/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            EntregaResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Entrega não encontrada"));
        }
    }
}