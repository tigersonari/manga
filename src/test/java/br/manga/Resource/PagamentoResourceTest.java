//OK

package br.manga.resource;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.manga.dto.ItemPedidoDTO;
import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;
import br.manga.dto.PedidoDTO;
import br.manga.service.PagamentoService;
import br.manga.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

    @Inject
    PagamentoService service;
    @Inject
    PedidoService pedidoService;

    static Long id;
static Long testPedidoId;

@BeforeEach
void setUp() {
   
    long uniqueNumeroPedido = System.currentTimeMillis() % 1000000; 
    ItemPedidoDTO item = new ItemPedidoDTO(1L, 1); 
    PedidoDTO pedidoDTO = new PedidoDTO(
        uniqueNumeroPedido, 
        LocalDate.now(),
        "ENTREGUE", 
        29.90, 
        1L, 
        List.of(item)
    );
    testPedidoId = pedidoService.create(pedidoDTO).id();
}

@Test
void testCreate() {
    PagamentoDTO dto = new PagamentoDTO(
        "CARTAO_CREDITO",
        "CONFIRMADO",
        LocalDate.now(),
        testPedidoId 
    );

    given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when().post("/pagamentos")
        .then()
            .log().all() 
            .statusCode(201)
            .body("id", notNullValue(),
                  "metodoPagamento", is("CARTAO_CREDITO"),
                  "status", is("CONFIRMADO"),
                  "pedidoId", is(testPedidoId.intValue()));
}

    @Test
    void testGetAll() {
        given()
            .when().get("/pagamentos")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetById() {
        given()
            .when().get("/pagamentos/1")
            .then()
                .statusCode(200)
                .body("metodoPagamento", is("CARTAO_CREDITO"));
    }

    @Test
    void testGetByStatus() {
        given()
            .when().get("/pagamentos/status/PENDENTE")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByPedido() {
        given()
            .when().get("/pagamentos/pedido/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testUpdate() {
        PagamentoDTO dto = new PagamentoDTO(
            "CARTAO_DEBITO",
            "PENDENTE",
            LocalDate.now(),
            testPedidoId 
        );
        id = service.create(dto).id();

        PagamentoDTO updated = new PagamentoDTO(
            "CARTAO_DEBITO",
            "CONFIRMADO",
            LocalDate.now(),
            testPedidoId
        );

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/pagamentos/" + id)
            .then()
                .statusCode(204);

        PagamentoResponseDTO response = service.findById(id);
        assertThat(response.metodoPagamento(), is("CARTAO_DEBITO"));
        assertThat(response.status(), is("CONFIRMADO"));
    }

    @Test
    void testDelete() {
        PagamentoDTO dto = new PagamentoDTO(
            "BOLETO",
            "PENDENTE",
            LocalDate.now(),
            testPedidoId 
        );
        id = service.create(dto).id();

        given()
            .when().delete("/pagamentos/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/pagamentos/" + id)
            .then()
                .statusCode(404);
    }
}
