package br.manga.resource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import br.manga.dto.PedidoDTO;
import br.manga.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PedidoResourceTest {

    @Inject
    PedidoService service;

    @Test
    void testCriarPedido() {
        PedidoDTO dto = new PedidoDTO(1L, null, "PENDENTE", null, null, null);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pedidos")
            .then()
                .statusCode(201)
                .body("status", is("PENDENTE"));
    }

    @Test
    void testBuscarPorStatus() {
        PedidoDTO dto = new PedidoDTO(2L, null, "EM_PROCESSAMENTO", null, null, null);
        service.create(dto);

        given()
            .when().get("/pedidos/status/EM_PROCESSAMENTO")
            .then()
                .statusCode(200)
                .body("[0].status", equalTo("EM_PROCESSAMENTO"));
    }

    @Test
    void testAtualizarPedido() {
        PedidoDTO dto = new PedidoDTO(3L, null, "PENDENTE", null, null, null);
        Long id = service.create(dto).id();

        PedidoDTO atualizado = new PedidoDTO(3L, null, "ENTREGUE", null, id, null);

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/pedidos/" + id)
            .then()
                .statusCode(204);
    }

    @Test
    public void testFindById() {
        Long pedidoId = 1L; 
        given()
                .when()
                .get("/pedidos/{id}", pedidoId)
                .then()
                .statusCode(200)
                .body("id", is(pedidoId.intValue()));
    }

    @Test
    public void testFindByNumero() {
        given()
                .when()
                .get("/pedidos/numero/{numeroPedido}", 1002L)
                .then()
                .statusCode(200)
                .body("numeroPedido", is(1002));
    }

    @Test
    void testDeletarPedido() {
        PedidoDTO dto = new PedidoDTO(4L, null, "CANCELADO", null, null, null);
        Long id = service.create(dto).id();

        given()
            .when().delete("/pedidos/" + id)
            .then()
                .statusCode(204);
    }
}


   