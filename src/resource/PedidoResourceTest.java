package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    PedidoService pedidoService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/pedidos")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        PedidoDTO dto = new PedidoDTO(
            1L, // idPedido
            "Sample Description", // description
            100.0, // totalAmount
            1L, // idUsuario
            java.util.List.of(1L, 2L) // list of item IDs
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pedidos")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/pedidos/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testBuscarPorUsuario() {
        given()
            .when().get("/pedidos/usuario/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testAlterar() {
        PedidoDTO dto = new PedidoDTO(
            1L, // idPedido
            "Updated Description", // description
            150.0, // totalAmount
            1L, // idUsuario
            java.util.List.of(3L, 4L) // list of item IDs
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/pedidos/" + id)
            .then()
                .statusCode(204);

        PedidoResponseDTO pedido = pedidoService.findById(id);
        assertThat(pedido.getIdUsuario(), is(1L));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/pedidos/" + id)
            .then()
                .statusCode(204);

        PedidoResponseDTO pedido = pedidoService.findById(id);
        assertNull(pedido);
    }
}
