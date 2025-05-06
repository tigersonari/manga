//OK

package br.manga.resource;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.manga.dto.EntregaDTO;
import br.manga.dto.ItemPedidoDTO;
import br.manga.dto.PedidoDTO;
import br.manga.service.EntregaService;
import br.manga.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EntregaResourceTest {

    @Inject
    EntregaService service;

    @Inject
    PedidoService pedidoService; 

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
        EntregaDTO dto = new EntregaDTO(
            "Rua Teste, 123",
            "BR" + System.currentTimeMillis(), 
            "PENDENTE",
            testPedidoId
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/entregas")
            .then()
                .log().all() 
                .statusCode(201)
                .body("id", notNullValue(),
                      "endereco", is("Rua Teste, 123"),
                      "status", is("PENDENTE"),
                      "pedidoId", is(testPedidoId.intValue()));
    }


    @Test
    void testGetAll() {
        given()
            .when().get("/entregas")
            .then()
                .statusCode(200);  
    }

    @Test
    void testGetById() {
        given()
            .when().get("/entregas/1")
            .then()
                .statusCode(200)
                .body("codigoRastreio", is("BR123456789"));
    }

    @Test
    void testGetByStatus() {
        given()
            .when().get("/entregas/status/ENTREGUE")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByCodigoRastreio() {
        given()
            .when().get("/entregas/rastreio/BR987654321")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByPedido() {
        given()
            .when().get("/entregas/pedido/1")
            .then()
                .statusCode(200);
    }

     @Test
    void testUpdate() {
        EntregaDTO dto = new EntregaDTO("Rua E, 202", "BR444555666", "PENDENTE", testPedidoId);
        Long id = service.create(dto).id();

        EntregaDTO updated = new EntregaDTO("Rua F, 303", "BR444555666", "ENTREGUE", testPedidoId);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/entregas/" + id)
            .then()
                .statusCode(204);

        br.manga.dto.EntregaResponseDTO response = service.findById(id);
        assertThat(response.endereco(), is("Rua F, 303"));
        assertThat(response.status(), is("ENTREGUE"));
    }

    @Test
    void testDelete() {
        EntregaDTO dto = new EntregaDTO("Rua G, 404", "BR777888999", "PENDENTE", testPedidoId);
        Long id = service.create(dto).id();

        given()
            .when().delete("/entregas/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/entregas/" + id)
            .then()
                .statusCode(404);
    }
}
