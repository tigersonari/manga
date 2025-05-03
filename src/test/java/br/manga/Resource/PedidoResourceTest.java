package br.manga.resource;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
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
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/pedidos")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        PedidoDTO pedido = new PedidoDTO(123L, LocalDate.now(), "PENDENTE", 100.0, 1L, Collections.emptyList());
        Long id = service.create(pedido).numeroPedido();

        given()
            .when().get("/pedidos/" + id)
            .then()
                .statusCode(200)
                .body("numeroPedido", is(123))
                .body("status", is("PENDENTE"));
    }

    @Test
    @Order(3)
    void testFindByUsuario() {
        PedidoDTO pedido = new PedidoDTO(124L, LocalDate.now(), "PENDENTE", 150.0, 1L, Collections.emptyList());
        service.create(pedido);

        given()
            .when().get("/pedidos/usuario/1")
            .then()
                .statusCode(200)
                .body("[0].numeroPedido", is(124));
    }

    @Test
    @Order(4)
    void testFindByStatus() {
        PedidoDTO pedido = new PedidoDTO(125L, LocalDate.now(), "ENVIADO", 200.0, 2L, Collections.emptyList());
        service.create(pedido);

        given()
            .when().get("/pedidos/status/ENVIADO")
            .then()
                .statusCode(200)
                .body("[0].numeroPedido", is(125));
    }

    @Test
    @Order(5)
    void testFindByNumeroPedido() {
        PedidoDTO pedido = new PedidoDTO(126L, LocalDate.now(), "PENDENTE", 250.0, 3L, Collections.emptyList());
        service.create(pedido);

        given()
            .when().get("/pedidos/numero/126")
            .then()
                .statusCode(200)
                .body("numeroPedido", is(126));
    }

    @Test
    @Order(6)
    void testCreate() {
        PedidoDTO pedido = new PedidoDTO(127L, LocalDate.now(), "NOVO", 300.0, 4L, Collections.emptyList());

        given()
            .contentType(ContentType.JSON)
            .body(pedido)
            .when().post("/pedidos")
            .then()
                .statusCode(201)
                .body("numeroPedido", is(127))
                .body("status", is("NOVO"))
                .body("valorTotal", is(300.0f));
    }

    static Long id = null;

    @Test
    @Order(7)
    void testUpdate() {
        PedidoDTO pedido = new PedidoDTO(128L, LocalDate.now(), "ORIGINAL", 400.0, 5L, Collections.emptyList());
        id = service.create(pedido).numeroPedido();

        PedidoDTO updated = new PedidoDTO(128L, LocalDate.now(), "ATUALIZADO", 450.0, 5L, Collections.emptyList());

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/pedidos/" + id)
            .then()
                .statusCode(204);

        PedidoResponseDTO response = service.findById(id);
        assertThat(response.status(), is("ATUALIZADO"));
        assertThat(response.valorTotal(), is(450.0));
    }

    @Test
    @Order(8)
    void testDelete() {
        PedidoDTO pedido = new PedidoDTO(129L, LocalDate.now(), "DELETAR", 500.0, 6L, Collections.emptyList());
        Long idDeletar = service.create(pedido).numeroPedido();

        given()
            .when().delete("/pedidos/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            PedidoResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Pedido não encontrado"));
        }
    }
}