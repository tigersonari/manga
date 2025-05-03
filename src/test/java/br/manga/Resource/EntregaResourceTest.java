package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.EntregaDTO;
import br.manga.dto.EntregaResponseDTO;
import br.manga.model.Pedido;
import br.manga.model.Usuario;
import br.manga.service.EntregaService;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EntregaResourceTest  {

    @Inject
    EntregaService service;

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
            .when().get("/entregas")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @io.quarkus.test.TestTransaction
    void testFindById() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Teste 123", "ABC123", "PENDENTE", pedidoId);
        Long id = service.create(entrega).id();

        given()
            .when().get("/entregas/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("codigoRastreio", is("ABC123"));
    }

    @Test
    @Order(3)
    @io.quarkus.test.TestTransaction
    void testFindByStatus() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Teste 124", "ABC124", "EM_TRANSITO", pedidoId);
        service.create(entrega);

        given()
            .when().get("/entregas/status/EM_TRANSITO")
            .then()
                .statusCode(200)
                .body("[0].codigoRastreio", is("ABC124"));
    }

    @Test
    @Order(4)
    @io.quarkus.test.TestTransaction
    void testFindByCodigoRastreio() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Teste 125", "ABC125", "PENDENTE", pedidoId);
        service.create(entrega);

        given()
            .when().get("/entregas/rastreio/ABC125")
            .then()
                .statusCode(200)
                .body("codigoRastreio", is("ABC125"));
    }

    @Test
    @Order(5)
    @io.quarkus.test.TestTransaction
    void testFindByPedido() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Teste 126", "ABC126", "ENTREGUE", pedidoId);
        service.create(entrega);

        given()
            .when().get("/entregas/pedido/" + pedidoId)
            .then()
                .statusCode(200)
                .body("[0].codigoRastreio", is("ABC126"));
    }

    @Test
    @Order(6)
    @io.quarkus.test.TestTransaction
    void testCreate() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Nova 127", "ABC127", "NOVO", pedidoId);

        given()
            .contentType(ContentType.JSON)
            .body(entrega)
            .when().post("/entregas")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("codigoRastreio", is("ABC127"))
                .body("status", is("NOVO"));
    }

    @Test
    @Order(7)
    @io.quarkus.test.TestTransaction
    void testUpdate() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Original 128", "ABC128", "ORIGINAL", pedidoId);
        Long id = service.create(entrega).id();

        EntregaDTO updated = new EntregaDTO("Rua Atualizada 128", "ABC128", "ATUALIZADO", pedidoId);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/entregas/" + id)
            .then()
                .statusCode(204);

        EntregaResponseDTO response = service.findById(id);
        assertThat(response.status(), is("ATUALIZADO"));
        assertThat(response.endereco(), is("Rua Atualizada 128"));
    }

    @Test
    @Order(8)
    @io.quarkus.test.TestTransaction
    void testDelete() {
        Long pedidoId = createValidPedido();
        EntregaDTO entrega = new EntregaDTO("Rua Deletar 129", "ABC129", "DELETAR", pedidoId);
        Long idDeletar = service.create(entrega).id();

        given()
            .when().delete("/entregas/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            EntregaResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Entrega não encontrada"));
        }
    }
}