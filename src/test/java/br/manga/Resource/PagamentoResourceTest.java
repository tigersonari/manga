package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.PagamentoDTO;
import br.manga.dto.PagamentoResponseDTO;
import br.manga.service.PagamentoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

    @Inject
    PagamentoService service;

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/pagamentos")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        PagamentoDTO pagamento = new PagamentoDTO("CARTAO", "APROVADO", LocalDate.now(), 1L);
        Long id = service.create(pagamento).id();

        given()
            .when().get("/pagamentos/" + id)
            .then()
                .statusCode(200)
                .body("id", is(equalTo(id.intValue())))
                .body("metodoPagamento", is("CARTAO"));
    }

    @Test
    @Order(3)
    void testFindByStatus() {
        PagamentoDTO pagamento = new PagamentoDTO("BOLETO", "PENDENTE", LocalDate.now(), 2L);
        service.create(pagamento);

        given()
            .when().get("/pagamentos/status/PENDENTE")
            .then()
                .statusCode(200)
                .body("[0].metodoPagamento", is("BOLETO"));
    }

    @Test
    @Order(4)
    void testFindByPedido() {
        PagamentoDTO pagamento = new PagamentoDTO("PIX", "APROVADO", LocalDate.now(), 3L);
        service.create(pagamento);

        given()
            .when().get("/pagamentos/pedido/3")
            .then()
                .statusCode(200)
                .body("[0].metodoPagamento", is("PIX"));
    }

    @Test
    @Order(5)
    void testCreate() {
        PagamentoDTO pagamento = new PagamentoDTO("CARTAO", "NOVO", LocalDate.now(), 4L);

        given()
            .contentType(ContentType.JSON)
            .body(pagamento)
            .when().post("/pagamentos")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("metodoPagamento", is("CARTAO"))
                .body("status", is("NOVO"));
    }

    static Long id = null;

    @Test
    @Order(6)
    void testUpdate() {
        PagamentoDTO pagamento = new PagamentoDTO("BOLETO", "ORIGINAL", LocalDate.now(), 5L);
        id = service.create(pagamento).id();

        PagamentoDTO updated = new PagamentoDTO("PIX", "ATUALIZADO", LocalDate.now(), 5L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/pagamentos/" + id)
            .then()
                .statusCode(204);

        PagamentoResponseDTO response = service.findById(id);
        assertThat(response.metodoPagamento(), is("PIX"));
        assertThat(response.status(), is("ATUALIZADO"));
    }

    @Test
    @Order(7)
    void testDelete() {
        PagamentoDTO pagamento = new PagamentoDTO("CARTAO", "DELETAR", LocalDate.now(), 6L);
        Long idDeletar = service.create(pagamento).id();

        given()
            .when().delete("/pagamentos/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            PagamentoResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Pagamento não encontrado"));
        }
    }
}