package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    PagamentoService pagamentoService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/pagamentos")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        PagamentoDTO dto = new PagamentoDTO(
            "Pix",
            "Descrição Alterada",
            java.time.LocalDate.now(),
            12345L
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pagamentos")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("formaPagamento", is("Cartão de Crédito"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/pagamentos/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testAlterar() {
        PagamentoDTO dto = new PagamentoDTO(
            "Pix",
            "Descrição Alterada",
            java.time.LocalDate.now(),
            12345L
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/pagamentos/" + id)
            .then()
                .statusCode(204);

        PagamentoResponseDTO pagamento = pagamentoService.findById(id);
        assertThat(pagamento.formaPagamento(), is("Pix"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/pagamentos/" + id)
            .then()
                .statusCode(204);

        PagamentoResponseDTO pagamento = pagamentoService.findById(id);
        assertNull(pagamento);
    }
}
