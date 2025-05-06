//OK

package br.manga.resource;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import br.manga.dto.ItemPedidoDTO;
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


    static Long id;

    @Test
    void testCreate() {
       
        ItemPedidoDTO item = new ItemPedidoDTO(1L, 1);
        PedidoDTO dto = new PedidoDTO(
            9999L, 
            LocalDate.now(),
            "ENTREGUE", 
            29.90, 
            1L, 
            List.of(item)
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pedidos")
            .then()
                .log().all() 
                .statusCode(201)
                .body("id", notNullValue(),
                      "numeroPedido", is(9999),
                      "status", is("ENTREGUE"),
                      "valorTotal", is(29.90f));
    }

    


    @Test
    void testGetAll() {
        given()
        .when().get("/pedidos")
        .then()
            .statusCode(200); 
    }

    @Test
    void testGetById() {
        given()
            .when().get("/pedidos/1")
            .then()
                .statusCode(200)
                .body("numeroPedido", is(1001));
    }

    @Test
    void testGetByUsuario() {
        given()
            .when().get("/pedidos/usuario/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByStatus() {
        given()
            .when().get("/pedidos/status/ENTREGUE")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByNumeroPedido() {
        given()
            .when().get("/pedidos/numero/1002")
            .then()
                .statusCode(200);
    }

    @Test
    void testUpdate() {
        ItemPedidoDTO item = new ItemPedidoDTO(1L, 2);
        PedidoDTO dto = new PedidoDTO(12345L, LocalDate.now(), "CANCELADO", 100.0, 1L, List.of(item));
        id = service.create(dto).id();

        PedidoDTO updated = new PedidoDTO(12345L, LocalDate.now(), "ENVIADO", 100.0, 1L, List.of(item));  // Ainda mantendo o item

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/pedidos/" + id)
            .then()
                .statusCode(204);

        PedidoResponseDTO response = service.findById(id);
        assertThat(response.status(), is("ENVIADO"));
    }

    @Test
    void testDelete() {
        ItemPedidoDTO item = new ItemPedidoDTO(1L, 2);
        PedidoDTO dto = new PedidoDTO(12345L, LocalDate.now(), "ENTREGUE", 100.0, 1L, List.of(item));
        id = service.create(dto).id();

        given()
            .when().delete("/pedidos/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/pedidos/" + id)
            .then()
                .statusCode(404);
    }
}
