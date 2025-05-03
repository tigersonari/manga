package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import br.manga.dto.PagamentoDTO;
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
    void testCriarPagamento() {
        PagamentoDTO dto = new PagamentoDTO("PIX", "Descricao", LocalDate.now(), 1L);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pagamentos")
            .then()
                .statusCode(201)
                .body("metodoPagamento", is("PIX"))
                .body("valor", is(100.0f));
    }

    @Test
    void testBuscarPorMetodo() {
        PagamentoDTO dto = new PagamentoDTO("BOLETO", "Descricao", LocalDate.now(), 1L);
        service.create(dto);

        given()
            .when().get("/pagamentos/metodo/BOLETO")
            .then()
                .statusCode(200)
                .body("[0].metodoPagamento", is("BOLETO"));
    }

    @Test
    void testAtualizarPagamento() {
        PagamentoDTO dto = new PagamentoDTO("CARTAO", "Descricao", LocalDate.now(), 1L);
        Long id = service.create(dto).id();

        PagamentoDTO atualizado = new PagamentoDTO("CARTAO", "Descricao Atualizada", LocalDate.now(), 1L);

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/pagamentos/" + id)
            .then()
                .statusCode(204);
    }

    @Test
    void testDeletarPagamento() {
        PagamentoDTO dto = new PagamentoDTO("PAYPAL", "Descricao", LocalDate.now(), 1L);
        Long id = service.create(dto).id();

        given()
            .when().delete("/pagamentos/" + id)
            .then()
                .statusCode(204);
    }
}