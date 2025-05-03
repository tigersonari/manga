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
import br.manga.model.Pedido;
import br.manga.model.Usuario;
import br.manga.service.PagamentoService;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

    @Inject
    PagamentoService service;

    private Long createValidPedido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Test User");
        usuario.setEmail("test@user.com");
        usuario.setSenhaHash("123");
        usuario.setEndereco("Rua Teste");
        Panache.getEntityManager().persist(usuario);

        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(999L);
        pedido.setData(LocalDate.now());
        pedido.setStatus("PROCESSANDO");
        pedido.setValorTotal(100.0);
        pedido.setUsuario(usuario);
        Panache.getEntityManager().persist(pedido);
        return pedido.getId();
    }

    @Test
    @Order(1)
    @io.quarkus.test.TestTransaction
    void testFindAll() {
        given()
            .when().get("/pagamentos")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @io.quarkus.test.TestTransaction
    void testFindById() {
        Long pedidoId = createValidPedido();
        PagamentoDTO pagamento = new PagamentoDTO("CARTAO", "APROVADO", LocalDate.now(), pedidoId);
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
    @io.quarkus.test.TestTransaction
    void testFindByStatus() {
        Long pedidoId = createValidPedido();
        PagamentoDTO pagamento = new PagamentoDTO("BOLETO", "PENDENTE", LocalDate.now(), pedidoId);
        service.create(pagamento);

        given()
            .when().get("/pagamentos/status/PENDENTE")
            .then()
                .statusCode(200)
                .body("[0].metodoPagamento", is("BOLETO"));
    }

    @Test
    @Order(4)
    @io.quarkus.test.TestTransaction
    void testFindByPedido() {
        Long pedidoId = createValidPedido();
        PagamentoDTO pagamento = new PagamentoDTO("PIX", "APROVADO", LocalDate.now(), pedidoId);
        service.create(pagamento);

        given()
            .when().get("/pagamentos/pedido/" + pedidoId)
            .then()
                .statusCode(200)
                .body("[0].metodoPagamento", is("PIX"));
    }

    @Test
    @Order(5)
    @io.quarkus.test.TestTransaction
    void testCreate() {
        Long pedidoId = createValidPedido();
        PagamentoDTO pagamento = new PagamentoDTO("CARTAO", "NOVO", LocalDate.now(), pedidoId);

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

    @Test
    @Order(6)
    @io.quarkus.test.TestTransaction
    void testUpdate() {
        Long pedidoId = createValidPedido();
        PagamentoDTO pagamento = new PagamentoDTO("BOLETO", "ORIGINAL", LocalDate.now(), pedidoId);
        Long id = service.create(pagamento).id();

        PagamentoDTO updated = new PagamentoDTO("PIX", "ATUALIZADO", LocalDate.now(), pedidoId);

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
    @io.quarkus.test.TestTransaction
    void testDelete() {
        Long pedidoId = createValidPedido();
        PagamentoDTO pagamento = new PagamentoDTO("CARTAO", "DELETAR", LocalDate.now(), pedidoId);
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