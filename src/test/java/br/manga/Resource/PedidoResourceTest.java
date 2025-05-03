package br.manga.resource;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.ItemPedidoDTO;
import br.manga.dto.PedidoDTO;
import br.manga.dto.PedidoResponseDTO;
import br.manga.model.Manga;
import br.manga.model.Usuario;
import br.manga.service.PedidoService;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PedidoResourceTest{

    @Inject
    PedidoService service;

    private Long[] createValidEntities() {
        Usuario usuario = new Usuario();
        usuario.setNome("Test User");
        usuario.setEmail("test@user.com");
        usuario.setSenhaHash("123");
        usuario.setEndereco("Rua Teste");
        Panache.getEntityManager().persist(usuario);

        Manga manga = new Manga();
        manga.setTitulo("Test Manga");
        manga.setIsbn("1234567890123");
        manga.setLancamento(LocalDate.now());
        manga.setPreco(29.90);
        manga.setSinopse("Teste");
        manga.setEstoque(br.manga.model.Estoque.DISPONIVEL);
        manga.setGenero(br.manga.model.Genero.SHOUNEN);
        manga.setClassificacao(br.manga.model.Classificacao.LIVRE);
        Panache.getEntityManager().persist(manga);

        return new Long[]{usuario.getId(), manga.getId()};
    }

    private PedidoDTO createValidPedidoDTO(Long usuarioId, Long mangaId) {
        return new PedidoDTO(
            999L,
            LocalDate.now(),
            "PROCESSANDO",
            100.0,
            usuarioId,
            List.of(new ItemPedidoDTO(mangaId, 1))
        );
    }

    @Test
    @Order(1)
    @io.quarkus.test.TestTransaction
    void testFindAll() {
        given()
            .when().get("/pedidos")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @io.quarkus.test.TestTransaction
    void testFindById() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = createValidPedidoDTO(usuarioId, mangaId);
        Long id = service.create(pedido).id();

        given()
            .when().get("/pedidos/" + id)
            .then()
                .statusCode(200)
                .body("numeroPedido", is(999))
                .body("status", is("PROCESSANDO"));
    }

    @Test
    @Order(3)
    @io.quarkus.test.TestTransaction
    void testFindByUsuario() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = createValidPedidoDTO(usuarioId, mangaId);
        service.create(pedido);

        given()
            .when().get("/pedidos/usuario/" + usuarioId)
            .then()
                .statusCode(200)
                .body("[0].numeroPedido", is(999));
    }

    @Test
    @Order(4)
    @io.quarkus.test.TestTransaction
    void testFindByStatus() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = new PedidoDTO(125L, 
        LocalDate.now(),
         "ENVIADO",
          200.0, 
          usuarioId, 
          List.of(new ItemPedidoDTO(mangaId, 1)));
        service.create(pedido);

        given()
            .when().get("/pedidos/status/ENVIADO")
            .then()
                .statusCode(200)
                .body("[0].numeroPedido", is(125));
    }

    @Test
    @Order(5)
    @io.quarkus.test.TestTransaction
    void testFindByNumeroPedido() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = createValidPedidoDTO(usuarioId, mangaId);
        service.create(pedido);

        given()
            .when().get("/pedidos/numero/999")
            .then()
                .statusCode(200)
                .body("numeroPedido", is(999));
    }

    @Test
    @Order(6)
    @io.quarkus.test.TestTransaction
    void testCreate() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = createValidPedidoDTO(usuarioId, mangaId);

        given()
            .contentType(ContentType.JSON)
            .body(pedido)
            .when().post("/pedidos")
            .then()
                .statusCode(201)
                .body("numeroPedido", is(999))
                .body("status", is("PROCESSANDO"))
                .body("valorTotal", is(100.0f));
    }

    @Test
    @Order(7)
    @io.quarkus.test.TestTransaction
    void testUpdate() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = createValidPedidoDTO(usuarioId, mangaId);
        Long id = service.create(pedido).id(); // Ajustado para usar id

        PedidoDTO updated = new PedidoDTO
        (999L, 
        LocalDate.now(), 
        "ATUALIZADO", 
        450.0, 
        usuarioId, 
        List.of(new ItemPedidoDTO(mangaId, 1)));

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
    @io.quarkus.test.TestTransaction
    void testDelete() {
        Long[] ids = createValidEntities();
        Long usuarioId = ids[0];
        Long mangaId = ids[1];
        PedidoDTO pedido = createValidPedidoDTO(usuarioId, mangaId);
        Long idDeletar = service.create(pedido).id(); 

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